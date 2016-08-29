package stubs.logic.fof;

import beans.PriceInfo;
import beans.ProfitStatisticsInfo;
import beans.ProfitStatisticsInfoOne;
import beans.ProfitStatisticsInfoTwo;
import bl.fof.FOFProfitStatisticsLogic;
import exception.ObjectNotFoundException;
import exception.ParameterException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Daniel on 2016/8/28.
 */
public class FOFProfitStasticsLogicStub extends UnicastRemoteObject implements
        FOFProfitStatisticsLogic {
    public FOFProfitStasticsLogicStub() throws RemoteException {

    }

    @Override
    public void setStartDate(String startDate) throws ParameterException, RemoteException {

    }

    @Override
    public void setEndDate(String endDate) throws ParameterException, RemoteException {

    }

    @Override
    public void setProformanceBase(String indexCode) throws RemoteException, ObjectNotFoundException {

    }

    @Override
    public void setUnitType() throws RemoteException {

    }

    @Override
    public ProfitStatisticsInfo getProfitStatisticsInfo() throws RemoteException {
        ProfitStatisticsInfo profitStatisticsInfo = new ProfitStatisticsInfo();
        ProfitStatisticsInfoOne profitStatisticsInfoOne = new ProfitStatisticsInfoOne();
        profitStatisticsInfoOne.relatedRise = 25;
        profitStatisticsInfoOne.reletedDrop = 23;
        profitStatisticsInfoOne.zeroRise = 1;
        profitStatisticsInfoOne.total = 49;
        ProfitStatisticsInfoTwo profitStatisticsInfoTwo = new ProfitStatisticsInfoTwo();
        profitStatisticsInfoTwo.relatedProfit = 20;
        profitStatisticsInfoTwo.combinationProfit = 30;
        profitStatisticsInfoTwo.baseProfit = 10;
        profitStatisticsInfoTwo.happenDate = "2016-08-23";
        profitStatisticsInfo.periodNum = profitStatisticsInfoOne;
        profitStatisticsInfo.average = profitStatisticsInfoOne;
        profitStatisticsInfo.aveSequence = profitStatisticsInfoOne;
        profitStatisticsInfo.maxSequence = profitStatisticsInfoOne;
        profitStatisticsInfo.percentage = profitStatisticsInfoOne;
        profitStatisticsInfo.standardDeviation = profitStatisticsInfoOne;
        profitStatisticsInfo.oneDrop = profitStatisticsInfoTwo;
        profitStatisticsInfo.twoDrop = profitStatisticsInfoTwo;
        profitStatisticsInfo.threeDrop = profitStatisticsInfoTwo;
        profitStatisticsInfo.oneRise = profitStatisticsInfoTwo;
        profitStatisticsInfo.twoRise = profitStatisticsInfoTwo;
        profitStatisticsInfo.threeRise = profitStatisticsInfoTwo;
        return profitStatisticsInfo;
    }

    @Override
    public List<PriceInfo> getPriceInfo() throws RemoteException {
        PriceInfo info = new PriceInfo();
        info.date = "2016-08-25";
        info.price = 1.5;
        info.total_netWorth = 3.5;
        info.rise = 0.04;
        PriceInfo info1 = new PriceInfo();
        info1.date = "2016-08-26";
        info1.price = 1.6;
        info1.total_netWorth = 3.6;
        info1.rise = 5.65;
        return Arrays.asList(info, info1);
    }
}
