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

    public PriceInfo copy() {
        PriceInfo info = new PriceInfo();
        info.date = this.date;
        info.price = this.price;
        info.rise = this.rise;
        info.total_netWorth = this.total_netWorth;
        return info;
    }
}
