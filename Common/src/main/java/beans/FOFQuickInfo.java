package beans;

import java.io.Serializable;

/**
 * Created by Daniel on 2016/8/26.
 */
public class FOFQuickInfo implements Serializable {
    /**
     * 基金代码
     */
    public String code;
    /**
     * 基金名字
     */
    public String name;
    /**
     * 业绩基准
     */
    public String performanceBase;
    /**
     * 创建日期
     */
    public String establish_date;
    /**
     * 净资产
     */
    public double netWorth;
    /**
     * 总回报
     */
    public double totalProfit;
}
