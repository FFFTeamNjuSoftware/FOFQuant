package beans;

import java.io.Serializable;

/**
 * Created by Daniel on 2016/8/21.
 */

public class ConstParameter implements Serializable{
    public double noRiskProfit;

    /**
     * 风险厌恶程度系数
     */
    public double riskDislikeFactor;
}
