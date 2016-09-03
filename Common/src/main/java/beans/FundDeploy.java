package beans;

import java.util.List;
import java.util.Map;

/**
 * Created by Seven on 16/9/3.
 */
public class FundDeploy {
    /*
    基金代码与其对应比重
     */
    public List<Map<String,Double>> proportion;

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
}
