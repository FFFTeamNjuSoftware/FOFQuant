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
    public double endHoldNum;
    /**
     * 持有市值
     */
    public double endHoldValue;
    /**
     * 持有比率
     */
    public double endHoldRatio;
    /**
     * 期末市价
     */
    public double endNetWorth;
    /**
     * 期间回报
     */
    public double periodProfit;
    /**
     * 期间回报率
     */
    public double periodProfitRate;
    /**
     * 期间浮动盈亏
     */
    public double periodFloatProfit;
    /**
     * 回报贡献
     */
    public double profitRatio;
    /**
     * 单位回报（回报贡献率/持有比率）
     */
    public double unitProfit;
}
