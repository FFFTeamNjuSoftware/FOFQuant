package beans;

import java.io.Serializable;

/**
 * Created by Daniel on 2016/8/21.
 */

public class ConstParameter implements Serializable{
    /**
     * 无风险利率
     */
    public double noRiskProfit;
    /**
     * 稳定值
     */
    public double stableIndex;
    /**
     * 低风险值
     */
    public double lowRiskIndex;
    /**
     * 高风险值
     */
    public double highRiskIndex;
    /**
     * 止损线
     */
    public double stopLossValue;
    /**
     * 最大回撤值
     */
    public double maxRetreatRatio;
    /**
     * 持有期
     */
    public double holdTime;
    /**
     * 窗口期
     */
    public double windowTime;

    /**
     * 风险厌恶程度系数
     */
    public double riskDislikeFactor;
}
