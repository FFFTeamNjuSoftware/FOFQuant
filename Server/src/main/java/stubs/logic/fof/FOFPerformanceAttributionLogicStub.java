package stubs.logic.fof;

import beans.PerformanceAttribution;
import bl.fof.FOFPerformanceAttributionLogic;
import exception.ObjectNotFoundException;
import exception.ParameterException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Daniel on 2016/8/28.
 */
public class FOFPerformanceAttributionLogicStub extends UnicastRemoteObject implements FOFPerformanceAttributionLogic {
    public FOFPerformanceAttributionLogicStub() throws RemoteException {
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
    public List<PerformanceAttribution> getPerformanceAttribution() throws RemoteException {
        PerformanceAttribution performanceAttribution = new PerformanceAttribution();
        performanceAttribution.fundCode = "000001";
        performanceAttribution.fundName = "测试";
        performanceAttribution.beginingHoldNum = 106232.3;
        performanceAttribution.beginingPerValue = 1.5;
        performanceAttribution.beginingTotalValue = 106232.3 * 1.5;
        performanceAttribution.endingHoldNum = 120000;
        performanceAttribution.endingPerValue = 1.6;
        performanceAttribution.endingTotalValue = 120000 * 1.6;
        performanceAttribution.periodProfit = 20.5;
        performanceAttribution.periodProfitFinishProfit = 12002;
        performanceAttribution.periodProfitRate = 10.5;
        performanceAttribution.beginingHoldRatio = 0.5;
        performanceAttribution.endingHoldRatio = 1.0;
        performanceAttribution.unitProfit = 100;
        return Arrays.asList(performanceAttribution);
    }
}
