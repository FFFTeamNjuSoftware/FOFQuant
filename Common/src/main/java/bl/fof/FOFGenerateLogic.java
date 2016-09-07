package bl.fof;

import beans.ProfitChartInfo;
import beans.RiskParameters;
import util.StrategyType;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

/**
 * Created by Daniel on 2016/8/26.
 */
public interface FOFGenerateLogic extends Remote {

    /**
     * 设定总资产
     *
     * @param assetValue
     */
    void setTotalAsset(double assetValue) throws RemoteException;

    /**
     * 设定策略类型
     *
     * @param strategyType
     */
    void setStrategyType(StrategyType strategyType) throws RemoteException;

    /**
     * 设定风险类型
     *
     * @param riskLevel
     */
    void setRiskLevel(RiskParameters riskLevel) throws RemoteException;

    /**
     * 返回大类配置的权重比
     *
     * @return
     */
    Map<String, Double> getLargeClassConfiguration() throws RemoteException;

    /**
     * 返回小类配置权重比
     *
     * @return
     */
    Map<String, Map<String, Double>> getSmallClassConfiguration() throws RemoteException;

    /**
     * 获得回测结果
     * values的值依次为基金收益率、指数收益率
     *
     * @return
     * @throws RemoteException
     */
    List<ProfitChartInfo> getTestValues() throws RemoteException;

    /**
     * 设置组合名字
     * @param name
     * @throws RemoteException
     */
    void setFOFName(String name) throws RemoteException;

    /**
     * 保存结果
     *
     * @throws RemoteException
     */
    void saveResult() throws RemoteException;
}
