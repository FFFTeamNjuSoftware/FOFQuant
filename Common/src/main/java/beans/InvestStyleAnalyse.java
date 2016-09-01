package beans;

import java.io.Serializable;

/**
 * Created by st0001 on 2016/9/1.
 */
public class InvestStyleAnalyse implements Serializable{
    public String code;
    public String name;
    /**
     * 投资风格
     */
    public String investStyle;
    /**
     * 平均持仓时间
     */
    public double aveHoldTime;
    /**
     * 持股市盈率
     */
    public double holdProfitRate;
    /**
     * 持股市净率
     */
    public double holdNetWorthRate;
    /**
     * 前10股票占比
     */
    public double topTenStockRate;
    /**
     * 前3行业占比
     */
    public double topThreeIndustryRate;
    /**
     * 前5行业占比
     */
    public double topFiveIndustryRate;
    /**
     * 前10行业占比
     */
    public double topTenIndustryRate;
    /**
     * 投资类型
     */
    public String investType;
    /**
     * 管理公司
     */
    public String manageCompany;
}
