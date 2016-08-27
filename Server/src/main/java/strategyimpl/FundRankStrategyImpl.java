package strategyimpl;

import beans.ConstParameter;
import beans.FundQuickInfo;
import beans.PriceInfo;
import bl.BaseInfoLogic;
import bl.MarketLogic;
import blimpl.BLController;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import strategy.FundRankStrategy;
import util.CalendarOperate;
import util.TimeType;
import util.UnitType;

import java.rmi.RemoteException;
import java.util.*;

/**
 * Created by Seven on 16/8/19.
 * 基金评级策略
 * 晨星评级 单一指标(总回报率,晨星风险系数,夏普比率)+综合评价
 */
public class FundRankStrategyImpl implements FundRankStrategy {
    private MarketLogic marketLogic;
    private BaseInfoLogic baseInfoLogic;

    public FundRankStrategyImpl(){
        marketLogic= BLController.getMarketLogic();
        baseInfoLogic=BLController.getBaseInfoLogic();
    }



    @Override
    public double getFundReturnRate(List<PriceInfo> priceInfoList, int month, TimeType timeType) throws RemoteException {
        double returnRate=1.0;

        for(int i=0;i<month;i++){
            returnRate=returnRate*(priceInfoList.get(i).rise/100+1);
        }
        returnRate=returnRate-1;
        return returnRate*100;
    }

    @Override
    public double getFundNoRiskRate(int month) throws RemoteException {
        ConstParameter constant=baseInfoLogic.getConstaParameteer();
        return constant.noRiskProfit;
    }

    @Override
    public double getFundProfit(List<PriceInfo> priceInfoList, int month,TimeType timeType) throws RemoteException{
        double returnRate=this.getFundReturnRate(priceInfoList,month,timeType);
        double noRiskRate=this.getFundNoRiskRate(month);
        double profit=(1+returnRate)/(1+noRiskRate)-1;
        return profit;
    }

    @Override
    public double getMRAR(String fundcode, TimeType timeType,String endDate) throws RemoteException{
        List<PriceInfo> priceInfos=null;
        System.out.println("开始计算基金"+fundcode);

        switch (timeType){
            case THREE_YEAR:
                try {
                        priceInfos=marketLogic.getPriceInfo(fundcode,UnitType.MONTH,"0000-00-00",endDate);
                } catch (ObjectNotFoundException e) {
                    System.out.println("基金"+fundcode+"没有对应的数据");
                    return 0.0;
                } catch (ParameterException e) {
                    System.out.println("starDate:\" + startDate + \" is behind endDate:\" + endDate");
                }
                if(priceInfos.size()<12*3) {
                    return 0;
                }else{
                    priceInfos = priceInfos.subList(priceInfos.size() - 12 * 3, priceInfos.size());
                }
                break;
            default:
                if(priceInfos.size()<12) {
                    return 0;
                }else{
                    priceInfos = priceInfos.subList(priceInfos.size() - 12, priceInfos.size());
                }
        }
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
                profit=this.getFundProfit(priceInfos,i+1,timeType);
                MRAR=MRAR*(1+profit);
            }
            MRAR=Math.pow(MRAR,12/T)-1;
        }else{
            for(int i=0;i<T;i++){
                profit=this.getFundProfit(priceInfos,i+1,timeType);
                MRAR=MRAR+Math.pow(1+profit,-riskDislikeFactor);
            }
            MRAR=Math.pow(MRAR/T,-12/T)-1;
        }
        return MRAR;
    }

    public Map<String ,Integer> refreshFundRank(TimeType timeType) throws RemoteException{
        Map<String,Integer> rank=this.getFundRankByDate(timeType,CalendarOperate.formatCalender(Calendar.getInstance()));
        return rank;
    }

    @Override
    public Map<String, Integer> getFundRankByDate(TimeType timeType,String endDate) throws RemoteException {
        Map<String,Double> index=new HashMap<>();
        Map<String,Integer> rank=new HashMap<>();
        List<String> sectorTypes=baseInfoLogic.getAllSectorType();
        List<FundQuickInfo> fundQuickInfos=null;
        List<String> codes=null;
        for (int i=0;i<sectorTypes.size();i++){
            try {
                fundQuickInfos=baseInfoLogic.getFundQuickInfo(sectorTypes.get(i));
            } catch (ObjectNotFoundException e) {
                System.out.println("没有"+sectorTypes.get(i)+" 类型对应的数据");
            }
            for (int j=0;i<fundQuickInfos.size();j++){
                codes.add(fundQuickInfos.get(j).code);
            }
            for(int k=0;k<codes.size();k++){
                index.put(codes.get(k),this.getMRAR(codes.get(k),timeType, endDate));
            }
            rank.putAll(this.Sequence(index));
        }
        return rank;
    }

    public Map<String,Integer> Sequence(Map<String,Double> index){
        Map<String,Integer> rank=new HashMap<>();
        List<Map.Entry<String,Double>> fundCodes=new ArrayList<Map.Entry<String, Double>>(index.entrySet());
        //按照降序排序
        Collections.sort(fundCodes, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                if(o2.getValue()>o1.getValue()){
                    return -1;
                }else if (o2.getValue()==o1.getValue()){
                    return 0;
                }else{
                    return 1;
                }
            }
        });

        //计算对应等级
        int size=fundCodes.size();
        for (int i=0;i<size;i++){
            String code=fundCodes.get(i).getKey();
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


    @Override
    public double getRiskIndex(String fundcode, TimeType timeType) throws RemoteException{
        double returnRate=0.0;
        double noRiskRate=0.0;
        int month=0;
        switch (timeType){
            case THREE_YEAR:
                month=12*3;
                break;
            default:
                month=12;
        }
        //获得同类基金的个数N
        int N=1;
        double tr=0.0;
        double dtr=0.0;
        double dr=0.0;
//        for(int i=0;i<month;i++) {
//            returnRate = this.getFundReturnRate(fundcode,i+1,timeType);
//            noRiskRate=this.getFundNoRiskRate(fundcode,i+1);
//            tr=(returnRate-noRiskRate<0)?(returnRate-noRiskRate):0;
//            dtr=-tr/month;
//        }
        return 0;
    }


}
