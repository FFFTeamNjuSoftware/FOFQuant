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
    Map<String,Double> proportion;

    /*
    总交易费用
     */
    double sumTradeFee;
}
