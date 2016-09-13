package strategy;

import beans.CPPIMarketDeploy;
import beans.RiskyParityDeploy;
import exception.NotInitialedException;

import java.rmi.RemoteException;

/**
 * Created by Seven on 16/9/3.
 */
public interface MarketDeployStrategy {
    /**
     * 当前日期开始生成CPPI策略
     * @param PortValue 产品组合初始值
     * @param RiskMulti CPPI策略风险乘数
     * @param GuaranteeRatio 产品保本率
     * @return
     */
    public CPPIMarketDeploy DefaultCPPIDeploy(double PortValue, double RiskMulti, double GuaranteeRatio) throws RemoteException, NotInitialedException;

    /**
     * 回测区间的CPPI策略
     * @param PortValue
     * @param RiskMulti
     * @param GuaranteeRatio
     * @param startDate
     * @param endDate
     * @return
     */
    public CPPIMarketDeploy CustomizedCPPIDeploy(int PortValue, double RiskMulti, double GuaranteeRatio, String startDate, String endDate) throws RemoteException, NotInitialedException;

    /**
     * 当前日期生成的风险平价策略
     * @return
     */
    public RiskyParityDeploy DefaultRiskyParityDeploy() throws RemoteException;

    /**
     * 回测区间的风险平价策略
     * @param startDate
     * @param endDate
     * @return
     */
    public RiskyParityDeploy CustomizedRiskyParityDeploy(String startDate,String endDate) throws RemoteException;
}
