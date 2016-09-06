package entities;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Seven on 16/9/4.
 */
public class CPPIMarketDeployEntity implements Serializable {
    Map<String,Double> proportion;
    List<Double> profits;
    double sumTradeFee;
    double profit;
    int adjustCycle;
    public CPPIMarketDeployEntity(Map<String,Double> proportion,List<Double> profits, double sumTradeFee,double profit,int adjustCycle){
            this.proportion=proportion;
            this.profits=profits;
            this.sumTradeFee=sumTradeFee;
            this.profit=profit;
            this.adjustCycle=adjustCycle;
    }

    public Map<String,Double> getProportion(){
        return proportion;
    }

    public List<Double> getProfits(){return profits;}

    public double getSumTradeFee(){
        return sumTradeFee;
    }

    public double getProfit(){return profit;}

    public int getAdjustCycle(){return adjustCycle;}
}
