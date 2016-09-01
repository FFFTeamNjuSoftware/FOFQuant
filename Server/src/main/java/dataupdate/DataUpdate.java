package dataupdate;

import beans.PriceInfo;
import beans.ProfitRateInfo;
import blimpl.BLController;
import dataservice.BaseInfoDataService;
import dataservice.MarketDataService;
import dataserviceimpl.DataServiceController;
import entities.FundInfosEntity;
import entities.FundQuickInfosEntity;
import entities.FundRankEntity;
import entities.NetWorthEntity;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import startup.HibernateBoot;
import strategyimpl.FundRankStrategyImpl;
import sun.nio.ch.Net;
import util.TimeType;
import util.UnitType;

import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Daniel on 2016/8/20.
 */
public class DataUpdate {

    /**
     * 更新基金的快速信息
     *
     * @throws IOException
     */
    public void updateQuickinfo() throws IOException {
        BaseInfoDataService baseInfoDataService = DataServiceController.getBaseInfoDataService();
        List<String> codes = baseInfoDataService.getAllCodes();
        Session se = HibernateBoot.openSession();
        Transaction tra = se.beginTransaction();
        for (String code : codes) {
            FundQuickInfosEntity fundQuickInfosEntity = new FundQuickInfosEntity();
            FundInfosEntity entity = null;
            try {
                entity = baseInfoDataService.getFundInfo(code);
            } catch (ObjectNotFoundException e) {
//                writer.write("FundInfo:" + code + "\n");
                e.printStackTrace();
                continue;
            }
            fundQuickInfosEntity.setFundCode(code);
            fundQuickInfosEntity.setSimpleName(entity.getSimpleName());
            PriceInfo priceInfo = null;
            try {
                priceInfo = BLController.getMarketLogic().getPriceInfo(code, UnitType
                        .DAY, 1).get(0);
            } catch (ParameterException e) {
                e.printStackTrace();
            } catch (ObjectNotFoundException e) {
//                writer.write("PriceInfo:" + code + "\n");
                e.printStackTrace();
                continue;
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
                continue;
            }
            fundQuickInfosEntity.setNetWorth(priceInfo.price);
            fundQuickInfosEntity.setDailyRise(priceInfo.rise);
            ProfitRateInfo profitRateInfo = null;
            try {
                profitRateInfo = BLController.getMarketLogic().getProfitRateInfo(code);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (ObjectNotFoundException e) {
//                writer.write("ProfitRateInfo:" + code + "\n");
                e.printStackTrace();
                continue;
            }
            fundQuickInfosEntity.setOneMonth(profitRateInfo.nearOneMonth);
            fundQuickInfosEntity.setThreeMonth(profitRateInfo.nearThreeMonth);
            fundQuickInfosEntity.setSixMonth(profitRateInfo.nearSixMonth);
            fundQuickInfosEntity.setOneYear(profitRateInfo.nearOneYear);
            fundQuickInfosEntity.setThreeYear(profitRateInfo.nearThreeYear);
            fundQuickInfosEntity.setFiveYear(profitRateInfo.nearFiveYear);
            fundQuickInfosEntity.setSinceEstablish(profitRateInfo.sinceEstablish);
            fundQuickInfosEntity.setYearRate(profitRateInfo.yearRate);
//            writer.flush();
            se.saveOrUpdate(fundQuickInfosEntity);
        }
        tra.commit();
    }

    /**
     * 更新基金的等级信息
     *
     * @throws IOException
     */
    public void updateFundRank() throws IOException {
        FundRankStrategyImpl fundRankStrategy = new FundRankStrategyImpl();
        Map<String, ArrayList<Double>> re = fundRankStrategy.refreshFundRank(TimeType.THREE_YEAR);
        Session se = HibernateBoot.openSession();
        Transaction transaction = se.beginTransaction();
        for (String key : re.keySet()) {
            FundRankEntity fundRankEntity = new FundRankEntity();
            fundRankEntity.setCode(key);
            fundRankEntity.setMrar(re.get(key).get(0));
            fundRankEntity.setGrade(re.get(key).get(1));
            fundRankEntity.setRank(re.get(key).get(2));
            se.saveOrUpdate(fundRankEntity);
        }
        transaction.commit();
        se.close();
    }

    /**
     * 更新基金净值信息
     *
     * @throws IOException
     */
    public void updateNetWorth() throws IOException {
        UpdateNetWorthFromJS updateNetWorthFromJS = new UpdateNetWorthFromJS();
        BaseInfoDataService baseInfoDataService = DataServiceController.getBaseInfoDataService();
        List<String> codes = baseInfoDataService.getAllCodes();
        int count = 0;
        for (String code : codes) {
            String startDate = baseInfoDataService.getMaxDate(code);
            startDate = (startDate == null ? "1000-01-01" : startDate);
            System.out.println(count++ + ":" + code + "," + startDate);
            updateNetWorthFromJS.updateNetWorth(code, startDate);
        }
    }

    public void updateFqWorth() {
        BaseInfoDataService baseInfoDataService = DataServiceController.getBaseInfoDataService();
        List<String> codes = baseInfoDataService.getAllCodes();
        int count = 0;
        for (String code : codes) {
            System.out.println(count++ + ":" + code);
            updateFqWorth(code);
        }
    }

    public void updateFqWorth(String code) {
        MarketDataService marketDataService = DataServiceController.getMarketDataService();
        Session se = HibernateBoot.openSession();
        Transaction transaction = se.beginTransaction();
        List li = se.createQuery("select max(date) from  NetWorthEntity where code=:code and " +
                "fqWorth is not null").setString("code", code).list();
        String startDate;
        if (li == null || li.size() == 0) {
            startDate = "1000-01-01";
        } else {
            startDate = (String) li.get(0);
        }
        try {
            System.out.println(code + "," + startDate);
            List<NetWorthEntity> netWorthEntities = marketDataService.getNetWorth(code, startDate,
                    LocalDate.now().toString());
            double rise = netWorthEntities.get(0).getFqWorth();
            for (int i = 1; i < netWorthEntities.size(); i++) {
                NetWorthEntity entity = netWorthEntities.get(i);
                rise = rise * (1 + entity.getDailyRise() / 100);
                entity.setFqWorth(rise);
                se.saveOrUpdate(entity);
            }
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        transaction.commit();
        se.close();
    }

    /**
     * 更新基金实时信息
     */
    public void updateFundRealTimeInfo() {
        UpdateFundRealTimeFromJS updateFundRealTimeFromJS = new UpdateFundRealTimeFromJS();
        BaseInfoDataService baseInfoDataService = DataServiceController.getBaseInfoDataService();
        List<String> codes = baseInfoDataService.getAllCodes();
        int count = 0;
        for (String code : codes) {
            System.out.println(count++ + ":" + code);
            updateFundRealTimeFromJS.updateFundRealTimeInfo(code);
        }
    }


    public static void main(String[] args) throws Exception {
        HibernateBoot.init();
        DataUpdate dataUpdate = new DataUpdate();
//        dataUpdate.updateQuickinfo();
//        dataUpdate.updateFundRank();
//        dataUpdate.updateNetWorth();
//        dataUpdate.updateFundRealTimeInfo();
        dataUpdate.updateFqWorth();
    }
}
