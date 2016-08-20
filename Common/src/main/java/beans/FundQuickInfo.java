package beans;

import java.io.Serializable;

/**
 * Created by Daniel on 2016/8/20.
 */
public class FundQuickInfo implements Serializable {
    /**
     * 基金代码
     */
    public String code;
    /**
     * 基金简称
     */
    public String simple_name;
    /**
     * 当前净值
     */
    public double current_netWorth;
    /**
     * 日涨幅
     */
    public double daily_rise;
    /**
     * 近一月收益
     */
    public double oneMonth;
    /**
     * 近三月收益
     */
    public double threeMonth;
    /**
     * 近半年收益
     */
    public double halfYear;
    /**
     * 近一年收益
     */
    public double oneYear;
    /**
     * 近三年收益
     */
    public double threeYear;
    /**
     * 近五年收益
     */
    public double fiveYear;
    /**
     * 自建立收益
     */
    public double sinceEstablish;
    /**
     * 年化收益（平均日收益*252）
     */
    public double yearRate;

}
