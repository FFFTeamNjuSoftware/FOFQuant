package blimpl.fof;

import beans.*;
import bl.fof.FOFGenerateLogic;
import com.mathworks.toolbox.javabuilder.MWException;
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
    public List<ProfitChartInfo> getTestValues(String startDate,String endDate) throws RemoteException, NotInitialedException, MWException {
        if(strategyType.equals(StrategyType.CPPI)) {
            CPPIMarketDeploy marketDeploy = marketDeployStrategy.CustomizedCPPIDeploy(asset, riskLevel.riskMulti, riskLevel.breakEvenValue, startDate, endDate);
            FundDeploy rightFundDeploy=fundDeployStrategy.CustomizedFundDeploy(startDate,endDate,SectorType.RIGHTS_TYPE);
            FundDeploy fixFundDeploy=fundDeployStrategy.CustomizedFundDeploy(startDate,endDate,SectorType.FIX_PROFIT_TYPE);
            int length=getMin(marketDeploy.profits.size(),rightFundDeploy.);
            for(int i=0;i<length;i++)
                CalendarOperate.nextDay(date);
        }else{
            RiskyParityDeploy riskyParityDeploy=marketDeployStrategy.CustomizedRiskyParityDeploy(startDate,endDate);
        }
        return null;
    }

    @Override
    public void setFOFName(String name) throws RemoteException {
        this.fofName=name;
    }

    @Override
    public void saveResult() throws RemoteException {

    }

    private int getMin(int a,int b,int c){
        int min=a;
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
