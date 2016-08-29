package stubs.logic.fof;

import beans.FundInFOFQuickInfo;
import bl.fof.FOFRealTimeMonitorLogic;
import exception.ObjectNotFoundException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Daniel on 2016/8/28.
 */
public class FOFRealTimeMonitorLogicStub extends UnicastRemoteObject implements FOFRealTimeMonitorLogic {
    public FOFRealTimeMonitorLogicStub() throws RemoteException {
    }

    @Override
    public List<FundInFOFQuickInfo> getFundInFOFQuickinfo() throws RemoteException {
        FundInFOFQuickInfo quickInfo = new FundInFOFQuickInfo();
        quickInfo.time = "2016-08-23 11:55:00";
        quickInfo.dayProfit = 0.5;
        quickInfo.cost = 20562.05;
        quickInfo.finishedProfit = 1512.05;
        quickInfo.floatProfit = 1562.65;
        quickInfo.fundCode = "000001";
        quickInfo.floatProfitRatio = 23;
        quickInfo.fundName = "测试";
        quickInfo.fundType = "股票型";
        quickInfo.holdNum = 1200;
        quickInfo.holdValue = 151556.5;
        quickInfo.newestWeight = 10;
        quickInfo.predictNetValue = 1.5;
        quickInfo.predictRiseValue = 0.06;
        quickInfo.totalProfit = 1555;
        quickInfo.totalProfitRatio = 35;
        return Arrays.asList(quickInfo);
    }

    @Override
    public void setProformanceBase(String indexCode) throws RemoteException, ObjectNotFoundException {

    }
}
