package entities;

import java.util.Map;

/**
 * Created by Seven on 16/9/4.
 */
public class CPPIMarketDeployEntity {
    Map<String,Double> proportion;
    double sumTradeFee;

    public CPPIMarketDeployEntity(Map<String,Double> proportion,double sumTradeFee){
            this.proportion=proportion;
            this.sumTradeFee=sumTradeFee;
    }

    public Map<String,Double> getProportion(){
        return proportion;
    }

    public double getSumTradeFee(){
        return sumTradeFee;
    }
}
