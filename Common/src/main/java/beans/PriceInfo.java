package beans;

import java.io.Serializable;

/**
 * Created by Daniel on 2016/8/15.
 */

/**
 * 价格信息
 */
public class PriceInfo implements Serializable {
    /**
     * 日期
     */
    public String date;
    /**
     * 价格
     */
    public double price;
    /**
     * 涨幅
     */
    public double rise;
    /**
     * 累计净值（基金有，指数没有）
     */
    public double total_netWorth;
}
