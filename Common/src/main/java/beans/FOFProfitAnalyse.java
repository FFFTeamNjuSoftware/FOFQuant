package beans;

import java.io.Serializable;

/**
 * Created by Daniel on 2016/8/25.
 */
public class FOFProfitAnalyse implements Serializable {
    /**
//     * 总回报率
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
    /**
     * 下行风险
     */
    public double downsideRisk;
    /**
     * 年化波动率
     */
    public double yearWaveRate;
    /**
     * 跟踪误差
     */
    public double trackingError;
    /**
     * 相关系数
     */
    public double correlationCoefficent;
    /**
     * alpha
     */
    public double alpha;
    /**
     * beta
     */
    public double beta;
    /**
     * shape
     */
    public double sharpe;
    /**
     * treynor
     */
    public double treynor;
    /**
     * r2
     */
    public double R2;
    /**
     * 半方差
     */
    public double semiVariance;
    /**
     *Sortino
     */
    public double sortino;

    /**
     * 詹森系数
     */
    public double Jensen;
}
