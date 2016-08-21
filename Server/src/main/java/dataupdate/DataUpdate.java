package dataupdate;

import beans.PriceInfo;
import beans.ProfitRateInfo;
import blimpl.BLController;
import dataservice.BaseInfoDataService;
import dataserviceimpl.DataServiceController;
import entities.FundInfosEntity;
import entities.FundQuickInfosEntity;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import startup.HibernateBoot;
import util.UnitType;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

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
//        BufferedWriter writer = null;
//        try {
//            writer = new BufferedWriter(new FileWriter(new File("D:/log.txt")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
            se.save(fundQuickInfosEntity);
        }
        tra.commit();
    }


    public void updateNetWorth() {

    }


    public static void main(String[] args) throws Exception {
        DataUpdate dataUpdate = new DataUpdate();
        dataUpdate.updateQuickinfo();
    }
}
