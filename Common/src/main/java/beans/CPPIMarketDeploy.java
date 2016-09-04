package beans;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Seven on 16/9/4.
 */
public class CPPIMarketDeploy  implements Serializable {

    /*
    大类基金配置比重
     */
    public Map<String,Double> proportion;

    /*
    总交易费用
     */
    public double sumTradeFee;

    /*
    总收益
     */
    public double profit;

    /*
     调整周期
     */
    public int adjustCycle;
}
