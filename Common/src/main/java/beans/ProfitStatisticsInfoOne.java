package beans;

import java.io.Serializable;

/**
 * Created by Daniel on 2016/8/26.
 */
public class ProfitStatisticsInfoOne implements Serializable {
    /**
     * 相对上涨
     */
    public double relatedRise;
    /**
     * 相对下跌
     */
    public double reletedDrop;
    /**
     * 平盘
     */
    public double zeroRise;
    /**
     * 合计
     */
    public double total;
}
