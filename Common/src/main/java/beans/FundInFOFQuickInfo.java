package beans;

import java.io.Serializable;

/**
 * Created by Daniel on 2016/8/25.
 */
public class FundInFOFQuickInfo implements Serializable {
    public String fundCode;
    public String fundName;
    public double predictNetValue;
    public double predictRiseValue;
    public double predictRise;
    public double holdNum;
    public double holdValue;
    public double dayProfit;
    public double floatProfit;
    public double floatProfitRatio;
    public double totalProfit;
    public double totalProfitRatio;
    public double finishedProfit;
    public double cost;
    public double totalValue;
    public String time;
}
