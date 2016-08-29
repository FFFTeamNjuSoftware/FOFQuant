package stubs.logic.fof;

import beans.FOFProfitAnalyse;
import bl.fof.FOFProfitAnalyseLogic;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import util.TimeType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Daniel on 2016/8/28.
 */

public class FOFProfitAnalyseLogicStub extends UnicastRemoteObject implements FOFProfitAnalyseLogic {
    public FOFProfitAnalyseLogicStub() throws RemoteException {

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
    public FOFProfitAnalyse getFOFProfitAnalyse(TimeType timeType) throws RemoteException {
        FOFProfitAnalyse fofProfitAnalyse = new FOFProfitAnalyse();
        fofProfitAnalyse.totalProfit = -0.3;
        fofProfitAnalyse.relatedTotalProfit = 2.63;
        fofProfitAnalyse.maxRise = 1.92;
        fofProfitAnalyse.maxRiseDays = 61;
        fofProfitAnalyse.maxRiseRecoverDays = 8;
        fofProfitAnalyse.minRise = -3.63;
        fofProfitAnalyse.minRiseDays = 10;
        fofProfitAnalyse.alpha = 0.56;
        fofProfitAnalyse.beta = 0.3;
        fofProfitAnalyse.correlationCoefficent = 0.6;
        fofProfitAnalyse.R2 = 0.6;
        fofProfitAnalyse.semiVariance = 1.2;
        fofProfitAnalyse.sharpe = 0.35;
        fofProfitAnalyse.sortino = 1.65;
        fofProfitAnalyse.trackingError = 1.3;
        fofProfitAnalyse.yearProfitRate = 20;
        fofProfitAnalyse.yearRelatedProfitRate = 10;
        fofProfitAnalyse.yearWaveRate = 0.26;
        fofProfitAnalyse.downsideRisk = 10.5;
        fofProfitAnalyse.treynor = 1.5;
        fofProfitAnalyse.minRiseRecoverDays = 8;
        fofProfitAnalyse.Jensen = 2.3;
        return fofProfitAnalyse;
    }
}
