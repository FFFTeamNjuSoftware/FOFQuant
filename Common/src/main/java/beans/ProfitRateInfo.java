package beans;

/**
 * Created by Daniel on 2016/8/18.
 */
public class ProfitRateInfo {

    /**
     * 收益率指标
     * 以下参数都是%，如返回0.5代表0.5%
     */
    /**
     * 近一月
     */
    public double nearOneMonth;
    /**
     * 近三月
     */
    public double nearThreeMonth;
    /**
     * 近六月
     */
    public double nearSixMonth;
    /**
     * 近一年
     */
    public double nearOneYear;
    /**
     * 近三年
     */
    public double nearThreeYear;
    /**
     * 近五年
     */
    public double nearFiveYear;
    /**
     * 自建立
     */
    public double sinceEstablish;
    /**
     * 年化收益
     */
    public double yearRate;
}
