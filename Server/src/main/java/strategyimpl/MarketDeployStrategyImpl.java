package strategyimpl;

import beans.PriceInfo;
import bl.BaseInfoLogic;
import bl.MarketLogic;
import blimpl.BLController;
import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import entities.CPPIMarketDeployEntity;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import strategy.MarketDeployStrategy;
import util.CalendarOperate;
import util.SectorType;
import util.UnitType;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Seven on 16/9/3.
 */
public class MarketDeployStrategyImpl implements MarketDeployStrategy{
    BaseInfoLogic baseInfoLogic;
    MarketLogic marketLogic;

    public MarketDeployStrategyImpl() {
        baseInfoLogic= BLController.getBaseInfoLogic();
        marketLogic=BLController.getMarketLogic();
    }

    @Override
    public CPPIMarketDeployEntity DefaultCPPIDeploy(double portValue, double riskMulti, double guaranteeRatio) throws RemoteException{
        String start="2013-01-01";
        String end= CalendarOperate.formatCalender(Calendar.getInstance());
        CPPIMarketDeployEntity cppiMarketDeployEntity=this.CustomizedCPPIDeploy(portValue,riskMulti,guaranteeRatio,start,end);
        return cppiMarketDeployEntity;
    }

    @Override
    public CPPIMarketDeployEntity CustomizedCPPIDeploy(double portValue, double riskMulti, double guaranteeRatio, String startDate, String endDate) throws RemoteException{
//        TradeDayOfYear: 产品每年交易日，如250天
        int tradeDayOfYear=250;
//        adjustCycle:产品根据模型调整周期，例如每10个交易日调整一次。
        int[] adjustCycle={30,60,90};
//        RisklessReturn: 无风险资产年化收益率
        double risklessReturn=0.005;
//        TradeFee:交易费用
        double tradeFee=0.005;

//        SData: 模拟风险资产收益序列，取上证指数的收益率
        CPPIMarketDeployEntity cppiMarketDeploy=null;
        String riskCode="000001";
        try {
            List<PriceInfo> priceInfoList=marketLogic.getPriceInfo(riskCode, UnitType.DAY,startDate,endDate);
            int size=priceInfoList.size();
            int tradeDayTimeLong=priceInfoList.size();
            double[] sData=new double[size];
            for(int i=0;i<size;i++){
                sData[i]=priceInfoList.get(i).rise;
            }
            MWNumericArray SData=new MWNumericArray(sData, MWClassID.DOUBLE);
            //调用CPPI策略matlab代码
            Object[] cppiResult=new Object[6];
//            cppiResult= MatlabBoot.getCalculateTool().(portValue,riskMulti,guaranteeRatio,tradeDayTimeLong,tradeDayOfYear,adjustCycle,risklessReturn,tradeFee,sData);
//            F:数组，第t个数据为t时刻安全底线
            double[] F=(double[]) ((MWNumericArray)cppiResult[0]).toDoubleArray();
//            E:数组，第t个数据为t时刻可投风险资产上限
            double[] E=(double[]) ((MWNumericArray)cppiResult[1]).toDoubleArray();
//            A:数组，第t个数据为t时刻产品净值
            double[] A=(double[]) ((MWNumericArray)cppiResult[2]).toDoubleArray();
//            G:数组，第t个数据为t时刻可投无g风险资产下限
            double[] G=(double[]) ((MWNumericArray)cppiResult[3]).toDoubleArray();
//            SumTradeFee：总交易费用
            double sumTradeFee=(double) cppiResult[4];
//            portFeez:组合交易是否出现平仓，0未 1出现
            double portFeez=(double) cppiResult[5];

            //计算收益率
            double profit=(A[size-1]-A[0])/A[0];
            //计算比重,单位为%
            double riskRate=E[0]/A[0];
            double risklessRate=G[0]/A[0];
            Map<String,Double> proportion=new HashMap<>();
            proportion.put(SectorType.FIX_PROFIT_TYPE,risklessRate);
            proportion.put(SectorType.RIGHTS_TYPE,riskRate);
            cppiMarketDeploy=new CPPIMarketDeployEntity(proportion,sumTradeFee);
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        } catch (ParameterException e) {
            e.printStackTrace();
        }


        return cppiMarketDeploy;
    }
}
