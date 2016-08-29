package dataupdate;

import beans.PriceInfo;
import beans.ProfitRateInfo;
import blimpl.BLController;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.JsonAdapter;
import com.mathworks.toolbox.javabuilder.external.org.json.JSONObject;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
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
        BufferedReader reader = new BufferedReader(new InputStreamReader(DataUpdate.class
                .getResourceAsStream("/extra_info/rank.txt")));
        String tem;
        Session se = HibernateBoot.openSession();
        while ((tem = reader.readLine()) != null) {
            String[] strs = tem.split(",");
            System.out.println(strs[0] + "," + strs[1]);
            se.createSQLQuery(String.format("UPDATE fund_infos set rank=%s WHERE code='%s'",
                    strs[1].substring(0, 1), strs[0])).executeUpdate();
        }
        se.close();
    }

    public void updateNetWorth() throws IOException {
        try {
            URL url = new URL("http://fund.eastmoney.com/f10/F10DataApi" +
                    ".aspx?type=lsjz&code=000001&page=1&per=100000&sdate=&edate=");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(true);
            conn.setRequestProperty("content-type", "text/html");
            conn.connect();
            BufferedReader reader=new BufferedReader(new InputStreamReader(conn
                    .getInputStream(), Charset.forName("gb2312")));
            String tem;
            while((tem=reader.readLine())!=null){
                System.out.println(tem);
            }
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
