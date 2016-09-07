package beans;

import java.io.Serializable;

/**
 * Created by Daniel on 2016/9/7.
 */
public class RiskParameters implements Serializable{
    /**
     * 风险乘数
     */
    public double riskMulti;
    /**
     * 保本额度
     */
    public double breakEvenValue;

    /**
     * 高中低三种类型风险参数
     */
    public static final RiskParameters HIGH_RISK = getRiskParameters(3.5, 0.8),
            MIDDLE_RISK = getRiskParameters(3.0, 0.9), LOW_RISK = getRiskParameters(2.5, 0.95);

    public static RiskParameters getRiskParameters(double... parameters) {
        RiskParameters riskParameters = new RiskParameters();
        riskParameters.riskMulti = parameters[0];
        riskParameters.breakEvenValue = parameters[1];
        return riskParameters;
    }
}
