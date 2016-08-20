package beans;

import java.io.Serializable;

/**
 * Created by Daniel on 2016/8/18.
 */
public class ProfitChartInfo implements Serializable{
    /**
     * 图表需要展示的数据
     */

    /**
     * 日期 yyyy-mm-dd
     */
    public String date;
    /**
     * 值，可能有多个值
     * 比如在折价溢价和万元波动图中有三个值，分别代表基金、基金指数、沪深300大盘指数
     */
    public double[] values;
}
