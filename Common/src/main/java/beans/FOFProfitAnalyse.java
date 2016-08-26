package beans;

import java.io.Serializable;

/**
 * Created by Daniel on 2016/8/25.
 */
public class FOFProfitAnalyse implements Serializable {
    /**
     * 总回报率
     */
    public double totalProfit;
    /**
     * 相对总回报
     */
    public double relatedTotalProfit;
    /**
     * 最大涨幅
     */
    public double maxRise;
    /**
     * 最大涨幅天数
     */
    public int maxRiseDays;
    /**
     * 最大涨幅复苏期间
     */
    public int maxRiseRecoverDays;
    /**
     * 最大跌幅
     */
    public double minRise;
    /**
     * 最大跌幅天数
     */
    public int minRiseDays;
    /**
     * 最大跌幅复苏期间
     */
    public int minRiseRecoverDays;
    /**
     * 年化平均回报
     */
    public double yearProfitRate;
    /**
     * 年化平均超额回报
     */
    public double yearRelatedProfitRate;
}
