package beans;

import util.StrategyType;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Seven on 16/9/3.
 */
public class FundDeploy implements Serializable {
    /*
    基金代码与其对应比重
     */
    public List<Map<String,Double>> proportion;

    /*
     基金组合对应的日收益率
     */
    public List<Double> profits;
    /*
    基金个数
     */
    public int fundNum;

    /*
    不同持有期对应的收益率
     */
    public double[] rpturn;

    /*
    窗口期
     */
    public int window;

    /*
    持有期
     */
    public int hold;

    /*
     夏普比率
     */
    public double sharpe;

    /*
    策略名称
     */
    public StrategyType strategy;
}
