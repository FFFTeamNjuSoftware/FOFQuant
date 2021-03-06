package beans;

/**
 * Created by Daniel on 2016/8/31.
 */

import java.io.Serializable;

/**
 * 风险收益指标 b
 */
public class RiskProfitIndex implements Serializable{
    public String code;
    public String name;
    public double alpha;
    public double beta;
    public double sharpe;
    public double treynor;
    public double jensen;
    /**
     * 平均收益率
     */
    public double aveProfit;
    /**
     * 平均风险收益率
     */
    public double aveRiskProfit;
    /**
     * 收益标准差
     */
    public double profitSd;
    /**
     * 年化收益标准差
     */
    public double yearWaveRate;
    /**
     * 投资类型
     */
    public String investType;
    /**
     * 管理公司
     */
    public String manageCompany;

}
