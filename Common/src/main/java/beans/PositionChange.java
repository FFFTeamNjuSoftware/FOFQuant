package beans;

import java.io.Serializable;

/**
 * Created by Daniel on 2016/8/26.
 */
public class PositionChange implements Serializable {
    /**
     * 调仓日期
     */
    public String changeDate;
    /**
     * 调仓时间
     */
    public String changeTime;
    /**
     * 基金代码
     */
    public String fundCode;
    /**
     * 基金名字
     */
    public String fundName;
    /**
     * 购买数量
     */
    public double buyNum;
    /**
     * 购买价格
     */
    public double buyPrice;
    /**
     * 卖出数量
     */
    public double saleNum;
    /**
     * 卖出价格
     */
    public double salePrice;
}
