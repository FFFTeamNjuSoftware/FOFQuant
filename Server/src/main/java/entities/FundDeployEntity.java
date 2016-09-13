package entities;

import util.StrategyType;

import java.util.List;
import java.util.Map;

/**
 * Created by Seven on 16/9/1.
 */
public class FundDeployEntity {
    //该配置组合所包含的基金在每个持有期所对应的权重
    private List<Map<String,Double>> proportion;
    //该配置组合所包含的基金个数
    private int fundNum;
    //该组合在不同的持有期内的收益率
    private double[] rpturn;
    private int window;
    private int hold;
    private double sharpe;
    private StrategyType strategy;
    private List<Double> profits;

    public FundDeployEntity(List<Map<String,Double>> proportion, List<Double> profits,int fundNum, double[] rpturn,int window, int hold, double sharpe,StrategyType strategy){
        this.proportion=proportion;
        this.profits=profits;
        this.fundNum=fundNum;
        this.rpturn=rpturn;
        this.window=window;
        this.hold=hold;
        this.sharpe=sharpe;
        this.strategy =strategy;
    }

    public List<Map<String,Double>> getProportion() { return proportion; }

    public int getFundNum(){
        return fundNum;
    }

    public double[] getRpturn(){
        return rpturn;
    }

    public int getWindow(){
        return window;
    }

    public int getHold(){
        return hold;
    }

    public double getSharpe(){
        return sharpe;
    }

    public StrategyType getStrategy(){return strategy;}

    public List<Double> getProfits(){return profits;}

}
