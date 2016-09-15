package strategyimpl;

import beans.CPPIMarketDeploy;
import beans.PriceInfo;
import beans.RiskyParityDeploy;
import bl.BaseInfoLogic;
import bl.MarketLogic;
import blimpl.BLController;
import blimpl.Converter;
import blimpl.LogicUtil;
import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import entities.CPPIMarketDeployEntity;
import entities.RiskyParityDeployEntity;
import exception.NotInitialedException;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import matlabtool.TypeConverter;
import startup.MatlabBoot;
import strategy.MarketDeployStrategy;
import util.CalendarOperate;
import util.SectorType;
import util.TimeType;
import util.UnitType;

import java.rmi.RemoteException;
import java.util.*;

/**
 * Created by Seven on 16/9/3.
 */
public class MarketDeployStrategyImpl implements MarketDeployStrategy {
    BaseInfoLogic baseInfoLogic;
    MarketLogic marketLogic;

    public MarketDeployStrategyImpl() {
        baseInfoLogic = BLController.getBaseInfoLogic();
        marketLogic = BLController.getMarketLogic();
    }

    @Override
    public CPPIMarketDeploy DefaultCPPIDeploy(double portValue, double riskMulti, double guaranteeRatio) throws RemoteException, NotInitialedException, MWException {
        String[] dates = LogicUtil.getDates(TimeType.ONE_YEAR);
        CPPIMarketDeploy cppiMarketDeploy = this.CustomizedCPPIDeploy(portValue, riskMulti,
                guaranteeRatio, dates[0], dates[1]);
        return cppiMarketDeploy;
    }

    @Override
    public CPPIMarketDeploy CustomizedCPPIDeploy(double portValue, double riskMulti, double guaranteeRatio, String startDate, String endDate) throws RemoteException, NotInitialedException, MWException {
//        TradeDayOfYear: 产品每年交易日，如250天
        int tradeDayOfYear = 250;
//        adjustCycle:产品根据模型调整周期，例如每10个交易日调整一次。
        int[] adjustCycle = {30, 60, 90};
//        RisklessReturn: 无风险资产年化收益率
        double risklessReturn = 0.037;
//        TradeFee:交易费用
        double tradeFee = 0.005;

//        SData: 模拟风险资产收益序列，取上证指数的收益率
        List<CPPIMarketDeployEntity> cppiMarketDeployEntities = new ArrayList<>();
        String riskCode = "I000001";
        try {
            System.out.println(startDate + "," + endDate);
            List<PriceInfo> priceInfoList = marketLogic.getPriceInfo(riskCode, UnitType.DAY, startDate, endDate);
            int size = priceInfoList.size();
            System.out.println(size);
            int tradeDayTimeLong = priceInfoList.size();
            double[] sData = new double[size];
            for (int i = 0; i < size; i++) {
                sData[i] = priceInfoList.get(i).rise;
            }

            MWNumericArray SData = new MWNumericArray(sData, MWClassID.DOUBLE);
            for (int i = 0; i < adjustCycle.length; i++) {
                //调用CPPI策略matlab代码
                Object[] cppiResult = MatlabBoot.getCalculateTool().CPPIStr(6, portValue + 0.0,
                        riskMulti + 0.0, guaranteeRatio + 0.0, tradeDayTimeLong + 0.0, tradeDayOfYear + 0.0, adjustCycle[i] + 0.0, risklessReturn + 0.0, tradeFee + 0.0, sData);
                //F:数组，第t个数据为t时刻安全底线
                double[] F = TypeConverter.getDoubleResultsRevert(cppiResult[0]);
                //E:数组，第t个数据为t时刻可投风险资产上限
                double[] E = TypeConverter.getDoubleResultsRevert(cppiResult[1]);
                //A:数组，第t个数据为t时刻产品净值
                double[] A = TypeConverter.getDoubleResultsRevert(cppiResult[2]);
                //G:数组，第t个数据为t时刻可投无风险资产下限
                double[] G = TypeConverter.getDoubleResultsRevert(cppiResult[3]);
                //SumTradeFee：总交易费用
                double sumTradeFee = TypeConverter.getSingleValue(cppiResult[4]);
                //portFeez:组合交易是否出现平仓，0未 1出现
                double portFeez = TypeConverter.getSingleValue(cppiResult[5]);

                //计算总体收益率
                double profit = (A[A.length - 1] - A[0]) / A[0];
                //计算每日收益率
                List<Double> profits = new ArrayList<>();
                for (int index = 0; index < E.length; index++) {
                    double dayProfit = (E[index] / A[index]) * sData[index] + (G[index] / A[index]) * risklessReturn;
                    profits.add(dayProfit);
                }

                //计算比重,以第一天为准,单位为%
                double riskRate = E[0] / A[0] * 100;
                double risklessRate = G[0] / A[0] * 100;
                Map<String, Double> proportion = new HashMap<>();
                proportion.put(SectorType.FIX_PROFIT_TYPE, risklessRate);
                proportion.put(SectorType.RIGHTS_TYPE, riskRate);
                CPPIMarketDeployEntity cppiMarketDeployEntity = new CPPIMarketDeployEntity(proportion, profits, sumTradeFee, profit, adjustCycle[i]);
                cppiMarketDeployEntities.add(cppiMarketDeployEntity);
            }
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        } catch (ParameterException e) {
            e.printStackTrace();
        }

        //选择收益率最大的调整周期
        double profit = 0;
        CPPIMarketDeployEntity cppiMarketDeployEntity = cppiMarketDeployEntities.get(0);
        for (CPPIMarketDeployEntity entity : cppiMarketDeployEntities) {
            if (entity.getProfit() > profit) {
                profit = entity.getProfit();
                cppiMarketDeployEntity = entity;
            }
        }

        return Converter.convertCPPIMarketDeployEntity(cppiMarketDeployEntity);
    }

    @Override
    public RiskyParityDeploy DefaultRiskyParityDeploy() throws RemoteException, NotInitialedException, MWException {
        //511010,885012
        String start = "2013-01-01";
        String end = CalendarOperate.formatCalender(Calendar.getInstance());
        RiskyParityDeploy deploy = this.CustomizedRiskyParityDeploy(start, end);
        return deploy;
    }

    @Override
    public RiskyParityDeploy CustomizedRiskyParityDeploy(String startDate, String endDate) throws RemoteException, NotInitialedException, MWException {
        String riskylesscode = "511010";
        String riskycode = "I885012";
        List<RiskyParityDeployEntity> entities = new ArrayList<>();
        try {
            List<PriceInfo> risklessInfo = marketLogic.getPriceInfo(riskylesscode, UnitType.DAY, startDate, endDate);
            List<PriceInfo> riskInfo = marketLogic.getPriceInfo(riskycode, UnitType.DAY, startDate, endDate);
            int length = riskInfo.size();
            if (risklessInfo.size() < riskInfo.size()) {
                length = risklessInfo.size();
            }
            double[][] pricedata = new double[length][2];
            for (int i = 0; i < length; i++) {
                pricedata[i][0] = risklessInfo.get(i).price;
                pricedata[i][1] = riskInfo.get(i).price;
            }

            int[] windows = {90, 180, 360};
            int[] holds = {30, 60, 90};
            for (int window : windows) {
                for (int hold : holds) {
                    for (int level = 1; level <= 5; level++) {
                        MWNumericArray dataset = new MWNumericArray(pricedata, MWClassID.DOUBLE);
                        Object[] result = MatlabBoot.getCalculateTool().MarketRiskyParity(3,
                                dataset, window + 0.0, hold + 0.0, level + 0.0);
                        double[][] w = (double[][]) ((MWNumericArray) result[0]).toDoubleArray();
                        System.out.println(result[0]);
                        double[][] tem = (double[][]) ((MWNumericArray) result[1]).toDoubleArray();
                        double[] rpturn = new double[tem.length];
                        for (int i = 0; i < tem.length; i++) {
                            rpturn[i] = tem[i][0];
                        }
                        double sharpe = ((MWNumericArray) result[2]).getDouble(1);

                        List<Map<String, Double>> proportions = new ArrayList<>();

                        for (int i = 0; i < rpturn.length; i++) {
                            Map<String, Double> proportion = new HashMap<>();
                            //w中每一行存储每一只基金对应的权重
                            proportion.put(riskylesscode, w[i][0]);
                            proportion.put(riskycode, w[i][1]);
                            proportions.add(proportion);
                        }

                        List<Double> risklessInfoProfit = new ArrayList<>();
                        List<Double> riskInfoProfit = new ArrayList<>();
                        for (int i = 1; i < length; i++) {
                            double risklessProfit = (risklessInfo.get(i).price - risklessInfo.get(i - 1).price) / risklessInfo.get(i - 1).price;
                            risklessInfoProfit.add(risklessProfit);
                            double riskProfit = (riskInfo.get(i).price - riskInfo.get(i - 1).price) / riskInfo.get(i - 1).price;
                            riskInfoProfit.add(riskProfit);
                        }
                        int step = (int) Math.floor(length / rpturn.length);
                        List<Double> profits = new ArrayList<>();
                        for (int i = 0; i < rpturn.length; i++) {
                            for (int j = i * step; j < (i + 1) * step; j++) {
                                double profit = risklessInfoProfit.get(i) * w[i][0] + riskInfoProfit.get(i) * w[i][1];
                                profits.add(profit);
                            }
                        }
                        RiskyParityDeployEntity riskyParityDeployEntity = new RiskyParityDeployEntity(proportions, 2, rpturn, window, hold, level, sharpe, profits);
                        entities.add(riskyParityDeployEntity);
                    }
                }
            }
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        } catch (ParameterException e) {
            e.printStackTrace();
        }

        double sharpe = 0;
        RiskyParityDeployEntity entity = entities.get(0);
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).getSharpe() > sharpe) {
                sharpe = entities.get(i).getSharpe();
                entity = entities.get(i);
            }
        }
        return Converter.convertRiskyParityDeployEntity(entity);
    }


}
