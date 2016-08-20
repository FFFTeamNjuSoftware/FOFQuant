package strategyimpl;

import beans.PriceInfo;
import bl.MarketLogic;
import blimpl.BLController;
import exception.ObjectNotFoundException;
import util.UnitType;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Seven on 16/8/19.
 * 基金评级策略
 * 风险指标R*收益指标E=风险收益指标
 * 基金公司指标&条件阈值=条件指标
 * 用条件指标过滤风险收益指标->排名指标
 */
public class FundRankStrategyImpl implements FundRankStrategy{
    private MarketLogic marketLogic;
    private static double RF=0.2996;
    private static int PERIOD=12;
    private static double PROFIT_INDEX=0;
    private static double RISK_INDEX=0;
    private static double A=0;

    private FundRankStrategyImpl(){
        marketLogic= BLController.getMarketLogic();
    }

    public double[] getFundGrowthRate(String fundcode, UnitType unitType) throws RemoteException, ObjectNotFoundException {
        List<PriceInfo> priceInfoList=marketLogic.getPriceInfo(fundcode, unitType);
        double[] rp=new double[priceInfoList.size()];
        int index=0;
        for(int i=0;i<priceInfoList.size();i++){
            rp[index]=priceInfoList.get(i).rise;
            index++;
        }
        return rp;
    }

    public double getFundProfit(String fundcode,int n,double rf) throws RemoteException, ObjectNotFoundException {
        double[] rp=this.getFundGrowthRate(fundcode,UnitType.MONTH);
        double rn=0.0;
        for(int i=0;i<n;i++){
            rn=rn+(rp[rp.length-i-1]-rf);
        }
        return rn;
    }

    public double getFundNegativeProfit(String fundcode,int n,double rf) throws RemoteException, ObjectNotFoundException {
        double rd=0.0;
        double rn=0.0;
        double[] rp=this.getFundGrowthRate(fundcode,UnitType.WEEK);
        for(int i=0;i<n*4;i++){
            rn=rp[rp.length-i-1]-rf;
            if(rn<0){
                rd=rd+rn;
            }
        }
        return rd;
    }

    public double getIndexE(String fundcode,int n,double e,double a) throws RemoteException, ObjectNotFoundException {
        double profit=0.0;
        double index=0.0;
        for (int i=0;i<n;i++){
            index=-e*this.getFundProfit(fundcode,i+1,RF);
            profit=profit+Math.pow(Math.E,index)*Math.pow(a,i);
        }
        profit=profit/n;
        return profit;
    }

    public double getIndexR(String fundcode,int n,double d) throws RemoteException, ObjectNotFoundException {
        double risk=0.0;
        double index=this.getFundNegativeProfit(fundcode,n,RF);
        risk=Math.pow(Math.E,index);
        return risk;
    }

    public double getIndexRE(String fundcode) throws RemoteException, ObjectNotFoundException {
        double R=this.getIndexR(fundcode,PERIOD,RISK_INDEX);
        double E=this.getIndexE(fundcode,PERIOD,PROFIT_INDEX,A);
        double RE=-R*E;
        return RE;
    }

    /**
     * 单只基金N周收益排名百分比Pj
     * @param fundcode
     * @param n
     * @return
     */
    public double getFundRankPercentage(String fundcode,int n){
        return 0.0;
    }

    /**
     * 基金公司旗下基金收益排名百分比均值Pk
     * @param fundcode
     * @param n
     * @return
     */
    public double getCompanyRankPercentageAve(String fundcode,int n){
        return 0.0;
    }

    /**
     * 条件指标D
     * @param fundcode
     * @param b
     * @return
     */
    public double getIndexD(String fundcode,double b){
        return 0.0;
    }

    /**
     * 最终的排名指标RI
     * @param fundcode
     * @return
     */
    public double getRankIndex(String fundcode){
        return 0.0;
    }
}
