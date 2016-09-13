package blimpl.fof;

import beans.*;
import bl.fof.FOFGenerateLogic;
import com.mathworks.toolbox.javabuilder.MWException;
import dataupdate.EstablishFOFDemo;
import exception.NotInitialedException;
import strategy.FundDeployStrategy;
import strategy.MarketDeployStrategy;
import strategyimpl.FundDeployStrategyImpl;
import strategyimpl.MarketDeployStrategyImpl;
import util.CalendarOperate;
import util.SectorType;
import util.StrategyType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Daniel on 2016/8/26.
 */
public class FOFGenerateLogicImpl extends UnicastRemoteObject implements FOFGenerateLogic {
    private MarketDeployStrategy marketDeployStrategy;
    private FundDeployStrategy fundDeployStrategy;
    private int asset=0;
    private RiskParameters riskLevel=RiskParameters.MIDDLE_RISK;
    private StrategyType strategyType=StrategyType.MARKET_RISKY_PARITY;
    private String fofName="The Best FOF";
    private String fofCode="000000";

    private FOFGenerateLogicImpl() throws RemoteException {
        marketDeployStrategy=new MarketDeployStrategyImpl();
        fundDeployStrategy=new FundDeployStrategyImpl();
    }

    private static FOFGenerateLogic instance;

    public static FOFGenerateLogic getInstance() {
        if (instance == null)
            try {
                instance = new FOFGenerateLogicImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        return instance;
    }

    @Override
    public void setTotalAsset(int assetValue) throws RemoteException {
        this.asset=assetValue;
    }

    @Override
    public void setStrategyType(StrategyType strategyType) throws RemoteException {
        this.strategyType=strategyType;
    }

    @Override
    public void setRiskLevel(RiskParameters riskLevel) throws RemoteException {
        this.riskLevel=riskLevel;
    }

    @Override
    public Map<String, Double> getLargeClassConfiguration() throws NotInitialedException, RemoteException, MWException {
        if(strategyType.equals(StrategyType.CPPI)) {
            CPPIMarketDeploy cppiMarketDeploy = marketDeployStrategy.DefaultCPPIDeploy(asset, riskLevel.riskMulti, riskLevel.breakEvenValue);
            return cppiMarketDeploy.proportion;
        }else{
            RiskyParityDeploy riskyParityDeploy=marketDeployStrategy.DefaultRiskyParityDeploy();
            //返回最后调整出来的权重
            return riskyParityDeploy.proportion.get(riskyParityDeploy.proportion.size()-1);
        }
    }

    @Override
    public Map<String, Map<String, Double>> getSmallClassConfiguration() throws NotInitialedException, RemoteException, MWException {
        FundDeploy rightFundDeploy=fundDeployStrategy.DefaultFundDeploy(SectorType.RIGHTS_TYPE);
        Map<String,Map<String,Double>> proportion=new HashMap<>();
        for(Map<String,Double> singleProportion:rightFundDeploy.proportion) {
            proportion.put(SectorType.RIGHTS_TYPE,singleProportion);
        }
        FundDeploy fixFundDeploy=fundDeployStrategy.DefaultFundDeploy(SectorType.FIX_PROFIT_TYPE);
        for(Map<String,Double> singleProportion:fixFundDeploy.proportion){
            proportion.put(SectorType.FIX_PROFIT_TYPE,singleProportion);
        }
        return proportion;
    }

    @Override
    public List<ProfitChartInfo> getTestValues(String startDate,String endDate) throws RemoteException, NotInitialedException, MWException {
        List<ProfitChartInfo> profitChartInfos=new ArrayList<>();
        FundDeploy rightFundDeploy=fundDeployStrategy.CustomizedFundDeploy(startDate,endDate,SectorType.RIGHTS_TYPE);
        FundDeploy fixFundDeploy=fundDeployStrategy.CustomizedFundDeploy(startDate,endDate,SectorType.FIX_PROFIT_TYPE);
        int length=0;
        List<Double> day_profits=new ArrayList<>();
        if(strategyType.equals(StrategyType.CPPI)) {
            CPPIMarketDeploy marketDeploy = marketDeployStrategy.CustomizedCPPIDeploy(asset, riskLevel.riskMulti, riskLevel.breakEvenValue, startDate, endDate);
            length=getMin(marketDeploy.profits.size(),rightFundDeploy.profits.size(),fixFundDeploy.profits.size());
            for(int i=0;i<length;i++) {
                double day_profit = this.getLargeClassConfiguration().get(SectorType.FIX_PROFIT_TYPE) * fixFundDeploy.profits.get(i)+this.getLargeClassConfiguration().get(SectorType.RIGHTS_TYPE)*rightFundDeploy.profits.get(i);
                day_profits.add(day_profit);
            }

            String date=startDate;
            for(int i=0;i<length;i++) {
                date = CalendarOperate.nextDay(date);
                ProfitChartInfo profitChartInfo=ProfitChartInfo.getProfitChartInfo(date,day_profits.get(i),marketDeploy.profits.get(i));
                profitChartInfos.add(profitChartInfo);
            }
        }else{
            RiskyParityDeploy riskyParityDeploy=marketDeployStrategy.CustomizedRiskyParityDeploy(startDate,endDate);
            length=getMin(riskyParityDeploy.profits.size(),rightFundDeploy.profits.size(),fixFundDeploy.profits.size());
            for(int i=0;i<length;i++) {
                double day_profit = this.getLargeClassConfiguration().get(SectorType.FIX_PROFIT_TYPE) * fixFundDeploy.profits.get(i)+this.getLargeClassConfiguration().get(SectorType.RIGHTS_TYPE)*rightFundDeploy.profits.get(i);
                day_profits.add(day_profit);
            }
            String date=startDate;
            for(int i=0;i<length;i++) {
                date = CalendarOperate.nextDay(date);
                ProfitChartInfo profitChartInfo=ProfitChartInfo.getProfitChartInfo(date,riskyParityDeploy.profits.get(i),rightFundDeploy.profits.get(i),fixFundDeploy.profits.get(i));
                profitChartInfos.add(profitChartInfo);
            }
        }
        return profitChartInfos;
    }

    @Override
    public void setFOFName(String name) throws RemoteException {
        this.fofName=name;
    }

    @Override
    public void setFOFCode(String code) throws RemoteException {
        this.fofCode=code;
    }

    @Override
    public void saveResult() throws RemoteException, NotInitialedException, MWException {
        Map<String,Double> market=this.getLargeClassConfiguration();
        Map<String,Map<String,Double>> fund=this.getSmallClassConfiguration();
        List<PositionInfo> positionInfos=new ArrayList<>();
        for (String type:market.keySet()){
            for(String code:fund.get(type).keySet()){
                double weight=market.get(type)*fund.get(type).get(code);
                PositionInfo positionInfo=PositionInfo.getPositonInfo(code,weight);
                positionInfos.add(positionInfo);
            }
        }
        FOFEstablishRequestInfo fofEstablishRequestInfo=new FOFEstablishRequestInfo();
        fofEstablishRequestInfo.positionInfos=positionInfos;
        fofEstablishRequestInfo.fofName=fofName;
        fofEstablishRequestInfo.fofCode=fofCode;
        fofEstablishRequestInfo.cashValueRatio=1;
        fofEstablishRequestInfo.totalInvestValue=asset;

        EstablishFOFDemo establishFOFDemo=new EstablishFOFDemo();
        establishFOFDemo.establishFOF(fofEstablishRequestInfo);
    }

    private int getMin(int a,int b,int c){
        int min;
        if(b<a) {
            if (b < c) {
                min = b;
            } else {
                min = c;
            }
        }else{
            if(c<a){
                min=c;
            }else{
                min=a;
            }
        }
        return min;
    }
}
