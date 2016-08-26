package beans;

import java.io.Serializable;

/**
 * Created by Daniel on 2016/8/25.
 */
public class AssetItem implements Serializable {
    /**
     * 基金代码
     */
    public String code;
    /**
     * 基金名字
     */
    public String name;
    /**
     * 持有量
     */
    public double holdValue;
    /**
     * 持有比率
     */
    public double ratio;
    /**
     * 总回报
     */
    public double totalProfit;
    /**
     * 总回报率
     */
    public double totalProfitRate;
}
