package beans;

import java.io.Serializable;

/**
 * Created by Daniel on 2016/9/2.
 */
public class FOFHistoryInfo implements Serializable {
    public String fofId;
    /**
     * 日期
     */
    public String date;
    /**
     * 总回报
     */
    public double totalProfit;
    /**
     * 总回报率
     */
    public double totalProfitRate;
    /**
     * 规模（份额）
     */
    public double scale;
    /**
     * 总资产
     */
    public double totalValue;
    /**
     * 日收益
     */
    public double dailyProfit;
    /**
     * 日收益率
     */
    public double dailyProfitRate;
}
