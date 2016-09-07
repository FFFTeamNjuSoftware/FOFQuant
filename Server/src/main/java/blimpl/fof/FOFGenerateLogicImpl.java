package blimpl.fof;

import beans.ProfitChartInfo;
import beans.RiskParameters;
import bl.fof.FOFGenerateLogic;
import util.StrategyType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

/**
 * Created by Daniel on 2016/8/26.
 */
public class FOFGenerateLogicImpl extends UnicastRemoteObject implements FOFGenerateLogic {
    private FOFGenerateLogicImpl() throws RemoteException {

    }

    private static FOFGenerateLogic instance;

    public static FOFGenerateLogic getInstance() {
        if (instance == null)
            try {
                instance = new FOFGenerateLogicImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        return instance;
    }

    @Override
    public Map<String, Double> getLargeClassConfiguration() {
        return null;
    }

    @Override
    public Map<String, Map<String, Double>> getSmallClassConfiguration() {
        return null;
    }

    @Override
    public void setTotalAsset(double assetValue) throws RemoteException {

    }

    @Override
    public void setStrategyType(StrategyType strategyType) throws RemoteException {

    }

    @Override
    public void setRiskLevel(RiskParameters riskLevel) throws RemoteException {

    }

    @Override
    public List<ProfitChartInfo> getTestValues() throws RemoteException {
        return null;
    }

    @Override
    public void setFOFName(String name) throws RemoteException {

    }

    @Override
    public void saveResult() throws RemoteException {

    }
}
