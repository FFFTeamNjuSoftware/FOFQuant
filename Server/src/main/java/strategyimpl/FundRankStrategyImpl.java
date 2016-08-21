package strategyimpl;

import beans.ConstParameter;
import beans.PriceInfo;
import bl.BaseInfoLogic;
import bl.MarketLogic;
import blimpl.BLController;
import exception.ObjectNotFoundException;
import util.TimeType;
import util.UnitType;

import java.rmi.RemoteException;
import java.util.*;

/**
 * Created by Seven on 16/8/19.
 * 基金评级策略
 * 风险指标R*收益指标E=风险收益指标
 * 基金公司指标&条件阈值=条件指标
 * 用条件指标过滤风险收益指标->排名指标
 */
public class FundRankStrategyImpl implements FundRankStrategy{
    private MarketLogic marketLogic;
    private BaseInfoLogic baseInfoLogic;

    private FundRankStrategyImpl(){
        marketLogic= BLController.getMarketLogic();
        baseInfoLogic=BLController.getBaseInfoLogic();
    }



    @Override
    public double getFundReturnRate(String fundcode, int month, TimeType timeType) throws RemoteException, ObjectNotFoundException {
        List<PriceInfo> priceInfoList=marketLogic.getPriceInfo(fundcode, UnitType.MONTH);
        double returnRate=1.0;
        switch (timeType){
            case THREE_YEAR:
                priceInfoList=priceInfoList.subList(priceInfoList.size()-12*3,priceInfoList.size());
                break;
            default:
                priceInfoList=priceInfoList.subList(priceInfoList.size()-12,priceInfoList.size());
        }
        for(int i=0;i<month;i++){
            returnRate=returnRate*(priceInfoList.get(i).rise+1);
        }
        returnRate=returnRate-1;
        return returnRate;
    }

    @Override
    public double getFundNoRiskRate(String fundcode, int month) throws RemoteException {
        ConstParameter constant=baseInfoLogic.getConstaParameteer();
        return constant.noRiskProfit;
    }

    @Override
    public double getFundProfit(String fundcode, int month,TimeType timeType) throws RemoteException, ObjectNotFoundException {
        double returnRate=this.getFundReturnRate(fundcode,month,timeType);
        double noRiskRate=this.getFundNoRiskRate(fundcode,month);
        double profit=(1+returnRate)/(1+noRiskRate)-1;
        return profit;
    }

    @Override
    public double getMRAR(String fundcode, TimeType timeType) throws RemoteException, ObjectNotFoundException {
        double riskDislikeFactor=baseInfoLogic.getConstaParameteer().riskDislikeFactor;
        double MRAR=1.0;
        double profit=0.0;
        int T;
        switch (timeType){
            case THREE_YEAR:
                T=12*3;
                break;
            default:
                T=12;
        }
        if (riskDislikeFactor==0){
            for(int i=0;i<T;i++){
                profit=this.getFundProfit(fundcode,i+1,timeType);
                MRAR=MRAR*(1+profit);
            }
            MRAR=Math.pow(MRAR,12/T)-1;
        }else{
            for(int i=0;i<T;i++){
                profit=this.getFundProfit(fundcode,i+1,timeType);
                MRAR=MRAR+Math.pow(1+profit,-riskDislikeFactor);
            }
            MRAR=Math.pow(MRAR/T,-12/T)-1;
        }
        return MRAR;
    }

    public Map<String ,Integer> refreshFundRank(TimeType timeType) throws RemoteException, ObjectNotFoundException {
        Map<String,Integer> rank=new HashMap<>();
        Map<String,Double> index=new HashMap<>();
        List<String> codes=baseInfoLogic.getFundCodes();
        for(int i=0;i<codes.size();i++){
            index.put(codes.get(i),this.getMRAR(codes.get(i),timeType));
        }
        List<Map.Entry<String,Double>> fundCodes=new ArrayList<Map.Entry<String, Double>>(index.entrySet());
        //按照降序排序
        Collections.sort(fundCodes, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                if(o2.getValue()>o1.getValue()){
                    return 1;
                }else if (o2.getValue()==o1.getValue()){
                    return 0;
                }else{
                    return -1;
                }
            }
        });

        //计算对应等级
        int size=fundCodes.size();
        for (int i=0;i<size;i++){
            String code=fundCodes.get(i).toString();
            int fundRank=0;
            if(i<=size*0.1){
                fundRank=5;
            }else if(size*0.1<i&&i<=size*0.325){
                fundRank=4;
            }else if(size*0.325<i&&i<=size*0.675){
                fundRank=3;
            }else if(size*0.675<i&&i<=size*0.9){
                fundRank=2;
            }else{
                fundRank=1;
            }
            rank.put(code,fundRank);
        }
        return rank;
    }
}