package beans;

import java.io.Serializable;

/**
 * Created by Daniel on 2016/8/26.
 */
public class ProfitStatisticsInfo implements Serializable {
    /**
     * 回报统计第一列内容
     */
    public ProfitStatisticsInfoOne periodNum, percentage, averate, standardDeviation, maxSequence,
            aveSequence;
    /**
     * 回报统计第二列内容
     */
    public ProfitStatisticsInfoTwo oneRise, twoRise, threeRise, oneDrop, twoDrop, threeDrop;

}
