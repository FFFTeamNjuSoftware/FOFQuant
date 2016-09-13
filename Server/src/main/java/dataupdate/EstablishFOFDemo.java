package dataupdate;

import beans.FOFEstablishRequestInfo;
import beans.PositionInfo;
import blimpl.LogicUtil;
import dataservice.MarketDataService;
import dataserviceimpl.DataServiceController;
import entities.*;
import exception.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import startup.HibernateBoot;
import util.FOFUtilInfo;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Daniel on 2016/9/3.
 */
public class EstablishFOFDemo {
    MarketDataService marketDataService = DataServiceController.getMarketDataService();
    //    String date = LocalDate.now().toString();
    String date = "2015-01-05";

    public void establishFOF(FOFEstablishRequestInfo establishRequestInfo) {
        Session se = HibernateBoot.openSession();
        Transaction tra = se.beginTransaction();
        FofInfoEntity fofInfoEntity = new FofInfoEntity();
        fofInfoEntity.setFofId(establishRequestInfo.fofCode);
        fofInfoEntity.setFofName(establishRequestInfo.fofName);
        fofInfoEntity.setNetAsset(establishRequestInfo.totalInvestValue);
        fofInfoEntity.setScale(establishRequestInfo.totalInvestValue);
        fofInfoEntity.setEstablishScale(establishRequestInfo.totalInvestValue);
        fofInfoEntity.setNetWorth(1.0);
        fofInfoEntity.setTotalProfit(0.0);
        fofInfoEntity.setEstablishDate(date);
        se.saveOrUpdate(fofInfoEntity);
        double investValue = establishRequestInfo.totalInvestValue;
        double cashValue = investValue * establishRequestInfo.cashValueRatio;
        for (PositionInfo positionInfo : establishRequestInfo.positionInfos) {
            try {
                NetWorthEntity netWorth = LogicUtil.getNetWorthByDate(marketDataService.getNetWorth
                        (positionInfo.fundCode), date);
                double inV = investValue * (1 - establishRequestInfo.cashValueRatio) *
                        positionInfo.weight;
                int buyNumber = (int) (inV / netWorth.getUnitWorth());
                double buyPrice = buyNumber * netWorth.getUnitWorth();
                cashValue += (inV - buyPrice);
                FofEstablishInfoEntity fofEstablishInfoEntity = new FofEstablishInfoEntity();
                FofHoldInfoEntity fofHoldInfoEntity = new FofHoldInfoEntity();
                fofEstablishInfoEntity.setBuyDate(date);
                fofEstablishInfoEntity.setFofCode(establishRequestInfo.fofCode);
                fofEstablishInfoEntity.setFundCode(positionInfo.fundCode);
                fofEstablishInfoEntity.setBuyNumber(buyNumber + 0.0);
                fofEstablishInfoEntity.setBuyPrice(netWorth.getUnitWorth());
                fofEstablishInfoEntity.setCost(buyPrice);
                fofEstablishInfoEntity.setOtherFee(0.0);
                fofHoldInfoEntity.setFofId(establishRequestInfo.fofCode);
                fofHoldInfoEntity.setFundId(positionInfo.fundCode);
                fofHoldInfoEntity.setDate(date);
                fofHoldInfoEntity.setNetWorth(netWorth.getUnitWorth());
                fofHoldInfoEntity.setHoldNum(buyNumber + 0.0);
                fofHoldInfoEntity.setHoldValue(buyPrice);
                fofHoldInfoEntity.setRatio(buyPrice / investValue * 100);
                fofHoldInfoEntity.setDayProfit(0.0);
                fofHoldInfoEntity.setFloatProfit(0.0);
                fofHoldInfoEntity.setFloatProfitRatio(0.0);
                fofHoldInfoEntity.setTotalProfit(0.0);
                fofHoldInfoEntity.setTotalProfitRatio(0.0);
                fofHoldInfoEntity.setFinishedProfit(0.0);
                se.saveOrUpdate(fofEstablishInfoEntity);
                se.saveOrUpdate(fofHoldInfoEntity);
            } catch (ObjectNotFoundException e) {
                e.printStackTrace();
            }
        }
        FofHistoryInfoEntity fofHistoryInfoEntity = new FofHistoryInfoEntity();
        fofHistoryInfoEntity.setFofId(FOFUtilInfo.FOF_CODE);
        fofHistoryInfoEntity.setDate(date);
        fofHistoryInfoEntity.setTotalProfit(0.0);
        fofHistoryInfoEntity.setTotalProfitRate(0.0);
        fofHistoryInfoEntity.setTotalValue(investValue);
        fofHistoryInfoEntity.setDailyProfit(0.0);
        fofHistoryInfoEntity.setDailyProfitRate(0.0);
        fofHistoryInfoEntity.setCashValue(cashValue);
        fofHistoryInfoEntity.setScale(investValue);
        fofInfoEntity.setCurrentCost(investValue - cashValue);
        NetWorthEntity netWorthEntity = new NetWorthEntity();
        netWorthEntity.setDate(date);
        netWorthEntity.setUnitWorth(1.0);
        netWorthEntity.setTotalWorth(1.0);
        netWorthEntity.setCode(establishRequestInfo.fofCode);
        netWorthEntity.setDailyRise(0.0);
        netWorthEntity.setFqWorth(1.0);
        se.saveOrUpdate(netWorthEntity);
        se.saveOrUpdate(fofInfoEntity);
        se.saveOrUpdate(fofHistoryInfoEntity);
        se.saveOrUpdate(fofInfoEntity);
        tra.commit();
    }

    public static void main(String[] args) {
        EstablishFOFDemo demo = new EstablishFOFDemo();
        FOFEstablishRequestInfo establishRequestInfo = new FOFEstablishRequestInfo();
        establishRequestInfo.fofCode = FOFUtilInfo.FOF_CODE;
        establishRequestInfo.fofName = "测试";
        establishRequestInfo.totalInvestValue = 10000000000.0;
        establishRequestInfo.cashValueRatio = 0;
        List<PositionInfo> infos = Arrays.asList(PositionInfo.getPositonInfo("165511", 0.07),
                PositionInfo.getPositonInfo("159910", 0.07), PositionInfo.getPositonInfo
                        ("070023", 0.07), PositionInfo.getPositonInfo("340006", 0.07), PositionInfo
                        .getPositonInfo("050021", 0.07), PositionInfo.getPositonInfo("233005", 0.13)
                , PositionInfo.getPositonInfo("166008", 0.13), PositionInfo.getPositonInfo
                        ("000122", 0.13), PositionInfo.getPositonInfo("092002", 0.13), PositionInfo
                        .getPositonInfo("000131", 0.13));
        establishRequestInfo.positionInfos = infos;
        demo.establishFOF(establishRequestInfo);
        HibernateBoot.closeConnection();
    }
}
