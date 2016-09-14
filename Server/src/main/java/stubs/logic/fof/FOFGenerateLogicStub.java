package stubs.logic.fof;

import beans.ProfitChartInfo;
import beans.RiskParameters;
import bl.fof.FOFGenerateLogic;
import util.SectorType;
import util.StrategyType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Daniel on 2016/8/28.
 */
public class FOFGenerateLogicStub extends UnicastRemoteObject implements FOFGenerateLogic {
    public FOFGenerateLogicStub() throws RemoteException {

    }

    @Override
    public void setFOFCode(String code) throws RemoteException {

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
    public List<ProfitChartInfo> getTestValues(String startDate,String endDate) throws
            RemoteException {
        return Arrays.asList(ProfitChartInfo.getProfitChartInfo("2015-01-01", 0.0, 0.0),
                ProfitChartInfo.getProfitChartInfo("2015-01-02", 0.3, 0.5), ProfitChartInfo
                        .getProfitChartInfo("2015-01-03", 0.4, 0.42));
    }

    @Override
    public void setFOFName(String name) throws RemoteException {

    }

    @Override
    public void saveResult() throws RemoteException {

    }

    @Override
    public Map<String, Double> getLargeClassConfiguration() throws RemoteException {
        Map<String, Double> configuration = new HashMap<>();
        configuration.put(SectorType.FIX_PROFIT_TYPE, 70.0);
        configuration.put(SectorType.RIGHTS_TYPE, 30.0);
        return configuration;
    }

    @Override
    public Map<String, Map<String, Double>> getSmallClassConfiguration() throws RemoteException {
        Map<String, Map<String, Double>> smallClassConfiguration = new HashMap<>();
        Map<String, Double> fix_profit = new HashMap<>();
        Map<String, Double> right = new HashMap<>();
        fix_profit.put("000122", 20.0);
        fix_profit.put("000131", 30.0);
        fix_profit.put("092002", 15.0);
        fix_profit.put("166008", 5.0);
        right.put("050021", 15.0);
        right.put("070023", 8.0);
        right.put("340006", 7.0);
        smallClassConfiguration.put(SectorType.FIX_PROFIT_TYPE, fix_profit);
        smallClassConfiguration.put(SectorType.RIGHTS_TYPE, right);
        return smallClassConfiguration;
    }
}
