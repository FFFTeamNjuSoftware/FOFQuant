package beans;

import java.io.Serializable;

/**
 * Created by Daniel on 2016/8/26.
 */
public class ProfitStatisticsInfoTwo implements Serializable {
    /**
     * 相对回报
     */
    public double relatedProfit;
    /**
     * 组合回报
     */
    public double combinationProfit;
    /**
     * 基准回报
     */
    public double baseProfit;
    /**
     * 发生日期
     */
    public String happenDate;
}
