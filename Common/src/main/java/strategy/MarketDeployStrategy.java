package strategy;

import beans.CPPIMarketDeploy;

import java.rmi.RemoteException;

/**
 * Created by Seven on 16/9/3.
 */
public interface MarketDeployStrategy {
    /**
     * 当前CPPI策略
     * @param PortValue 产品组合初始值
     * @param RiskMulti CPPI策略风险乘数
     * @param GuaranteeRatio 产品保本率
     * @return
     */
    public CPPIMarketDeploy DefaultCPPIDeploy(double PortValue, double RiskMulti, double GuaranteeRatio) throws RemoteException;

    /**
     * 回测区间CPPI策略
     * @param PortValue
     * @param RiskMulti
     * @param GuaranteeRatio
     * @param startDate
     * @param endDate
     * @return
     */
    public CPPIMarketDeploy CustomizedCPPIDeploy(double PortValue, double RiskMulti, double GuaranteeRatio, String startDate, String endDate) throws RemoteException;
}
