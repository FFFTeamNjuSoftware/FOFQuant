package beans;

import java.io.Serializable;

/**
 * Created by Daniel on 2016/8/25.
 */
public class PerformanceAttribution implements Serializable {
    /**
     * 基金代码
     */
    public String fundCode;
    /**
     * 基金名字
     */
    public String fundName;
    /**
     * 期初市价
     */
    public double beginingPerValue;
    /**
     * 期初持仓
     */
    public double beginingHoldNum;
    /**
     * 期初市值
     */
    public double beginingTotalValue;
    /**
     * 期末市价
     */
    public double endingPerValue;
    /**
     * 期末持仓
     */
    public double endingHoldNum;
    /**
     * 期末市值
     */
    public double endingTotalValue;
    /**
     * 本期盈亏
     */
    public double periodProfit;
    /**
     * 本期回报率
     */
    public double periodProfitRate;
    /**
     * 本期已实现盈亏
     */
    public double periodProfitFinishProfit;
}
