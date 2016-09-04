package entities;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Seven on 16/9/4.
 */
public class CPPIMarketDeployEntity implements Serializable {
    Map<String,Double> proportion;
    double sumTradeFee;
    double profit;
    int adjustCycle;
    public CPPIMarketDeployEntity(Map<String,Double> proportion, double sumTradeFee,double profit,int adjustCycle){
            this.proportion=proportion;
            this.sumTradeFee=sumTradeFee;
            this.profit=profit;
            this.adjustCycle=adjustCycle;
    }

    public Map<String,Double> getProportion(){
        return proportion;
    }

    public double getSumTradeFee(){
        return sumTradeFee;
    }

    public double getProfit(){return profit;}

    public int getAdjustCycle(){return adjustCycle;}
}
