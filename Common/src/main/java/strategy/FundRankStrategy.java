package strategy;

import exception.ObjectNotFoundException;
import util.TimeType;

import java.rmi.RemoteException;
import java.util.Map;

/**
 * Created by Seven on 16/8/20.
 * 基金评级策略
 * 风险指标R*收益指标E=风险收益指标
 * 基金公司指标&条件阈值=条件指标
 * 用条件指标过滤风险收益指标->排名指标
 */
public interface FundRankStrategy {

    /**
     * 获得基金第t月的总回报率TRt
     * @param fundcode
     * @param month
     * @return
     * @throws RemoteException
     * @throws ObjectNotFoundException
     */
    public double getFundReturnRate(String fundcode, int month,TimeType timeType) throws RemoteException, ObjectNotFoundException;

    /**
     * 获得基金第t月的无风险资产收益率Rbt
     * @param fundcode
     * @param month
     * @return
     */
    public double getFundNoRiskRate(String fundcode,int month) throws RemoteException;

    /**
     * 获得第t月的几何超额收益rGt
     * @param fundcode
     * @param month
     * @return
     * @throws RemoteException
     * @throws ObjectNotFoundException
     */
    public double getFundProfit(String fundcode,int month,TimeType timeType) throws RemoteException, ObjectNotFoundException;

    /**
     * 获得基金近三年、近三年风险调整后收益 MRAR
     * @param fundcode
     * @param timeType
     * @return
     */
    public double getMRAR(String fundcode, TimeType timeType) throws RemoteException, ObjectNotFoundException;

    /**
     * 更新基金评级
     * @param timeType
     * @return
     * @throws RemoteException
     * @throws ObjectNotFoundException
     */
    public Map<String ,Integer> refreshFundRank(TimeType timeType) throws RemoteException, ObjectNotFoundException;

}
