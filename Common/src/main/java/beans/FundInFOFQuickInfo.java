package beans;

import java.io.Serializable;

/**
 * Created by Daniel on 2016/8/25.
 */
public class FundInFOFQuickInfo implements Serializable {
    /**
     * 基金代码
     */
    public String fundCode;
    /**
     * 基金名字
     */
    public String fundName;
    /**
     * 基金类型
     */
    public String fundType;
    /**
     * 预测净值
     */
    public double predictNetValue;
    /**
     * 预测涨跌
     */
    public double predictRiseValue;
    /**
     * 预测涨跌幅
     */
    public double predictRise;
    /**
     * 持有量
     */
    public double holdNum;
    /**
     * 持仓市值
     */
    public double holdValue;
    /**
     * 最新权重
     */
    public double newestWeight;
    /**
     * 当日盈亏
     */
    public double dayProfit;
    /**
     * 浮动盈亏
     */
    public double floatProfit;
    /**
     * 浮动盈亏率
     */
    public double floatProfitRatio;
    /**
     * 累计盈亏
     */
    public double totalProfit;
    /**
     * 累计盈亏率
     */
    public double totalProfitRatio;
    /**
     * 已实现盈亏
     */
    public double finishedProfit;
    /**
     * 持仓成本
     */
    public double cost;
    /**
     * 更新时间
     */
    public String time;
}
