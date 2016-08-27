package beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Daniel on 2016/8/26.
 */
public class LookBackTestingResult implements Serializable {
    /**
     * 波动率
     */
    public double waveRate;
    /**
     * 年化收益率
     */
    public double yearRate;
    /**
     * 夏普指数
     */
    public double sharpe;
    /**
     * 最大回撤率
     */
    public double maxCancelRate;
    /**
     * 回测的图表信息
     */
    public List<PriceInfo> combinationPriceInfo,basePriceInfo;
}
