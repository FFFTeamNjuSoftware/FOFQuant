package dataupdate;

import beans.FundRealTimeInfo;
import beans.PriceInfo;
import beans.ProfitRateInfo;
import blimpl.BLController;
import com.google.gson.Gson;
import dataservice.BaseInfoDataService;
import dataserviceimpl.DataServiceController;
import entities.FundInfosEntity;
import entities.FundQuickInfosEntity;
import entities.FundRankEntity;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import org.dom4j.io.SAXReader;
import org.hibernate.Session;
import org.hibernate.Transaction;
import startup.HibernateBoot;
import strategyimpl.FundRankStrategyImpl;
import util.TimeType;
import util.UnitType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public void updateNetWorth() throws IOException {
        try {
            URL url = new URL("http://fundgz.1234567.com.cn/js/270010.js?rt=1472009215144");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(true);
            conn.setRequestProperty("content-type", "text/html");
            conn.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn
                    .getInputStream(), Charset.forName("utf8")));
            SAXReader reader1 = new SAXReader();
            String tem = reader.readLine();
            System.out.println(tem);
            Pattern pattern = Pattern.compile("\\{.*\\}");
            Matcher matcher = pattern.matcher(tem);
            matcher.find();
            String content = matcher.group();
            FundRealTimeInfo fundRealTime = new Gson().fromJson(content, FundRealTimeInfo.class);
            System.out.println(new Gson().toJson(fundRealTime));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        HibernateBoot.init();
        DataUpdate dataUpdate = new DataUpdate();
//        dataUpdate.updateQuickinfo();
//        dataUpdate.updateFundRank();
        dataUpdate.updateNetWorth();
        HibernateBoot.closeConnection();
    }
}
