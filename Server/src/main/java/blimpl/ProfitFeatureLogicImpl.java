package blimpl;

import CalculateTool.CalculateTool;
import beans.InvestStyleAnalyse;
import beans.PriceInfo;
import beans.RiskProfitIndex;
import bl.MarketLogic;
import bl.ProfitFeatureLogic;
import bl.RiskFeatureLogic;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import dataservice.BaseInfoDataService;
import dataserviceimpl.DataServiceController;
import entities.ConstParameterEntity;
import entities.FundInfosEntity;
import exception.NotInitialedException;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import matlabtool.TypeConverter;
import startup.MatlabBoot;
import util.CalendarOperate;
import util.NumberOpe;
import util.TimeType;
import util.UnitType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by Daniel on 2016/8/15.
 */
public class ProfitFeatureLogicImpl extends UnicastRemoteObject implements ProfitFeatureLogic {

    private static ProfitFeatureLogic instance;
    private MarketLogic marketLogic;
    private BaseInfoDataService baseInfoDataService;
    private String baseCode;

    private ProfitFeatureLogicImpl() throws RemoteException {
        marketLogic = BLController.getMarketLogic();
        baseInfoDataService = DataServiceController.getBaseInfoDataService();
        baseCode = "I000300";
    }

    public static ProfitFeatureLogic getInstance() {
        if (instance == null)
            try {
                instance = new ProfitFeatureLogicImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        return instance;
    }

    @Override
    public double getAlpha(String code) throws RemoteException, ObjectNotFoundException {
        AlphaBetaCal alphaBetaCal = new AlphaBetaCal(code);
        double[] re = alphaBetaCal.getAlphaBeta();
        if (re == null) {
            throw new ObjectNotFoundException("");
        }
        return re[0];
    }

    @Override
    public double aveProfitRate(String code) throws RemoteException, ObjectNotFoundException {
        Calendar calendar = Calendar.getInstance();
        String date1 = CalendarOperate.formatCalender(calendar);
        calendar.add(Calendar.YEAR, -1);
        String date2 = CalendarOperate.formatCalender(calendar);
        List<PriceInfo> infos = null;
        try {
            infos = marketLogic.getPriceInfo(code, UnitType.DAY, date2, date1);
        } catch (ParameterException e) {
            e.printStackTrace();
        }
        double ave_rise = 0;
        int n = infos.size();
        for (PriceInfo info : infos) {
            ave_rise += info.rise / 100;
        }
        ave_rise = ave_rise / n;
        return NumberOpe.controlDecimalDouble(ave_rise, 2);
    }

    @Override
    public double riskProfitRate(String code) throws RemoteException, ObjectNotFoundException {
        return 0;
    }

    @Override
    public double getEnsembleAveProfitRate(String code) throws RemoteException, ObjectNotFoundException {
        Calendar calendar = Calendar.getInstance();
        String date1 = CalendarOperate.formatCalender(calendar);
        calendar.add(Calendar.YEAR, -1);
        String date2 = CalendarOperate.formatCalender(calendar);
        List<PriceInfo> infos = null;
        try {
            infos = marketLogic.getPriceInfo(code, UnitType.DAY, date2, date1);
        } catch (ParameterException e) {
            e.printStackTrace();
        }
        double ave_rise = 1;
        int n = infos.size();
        for (PriceInfo info : infos) {
            ave_rise *= (1 + info.rise / 100);
        }
        ave_rise = Math.pow(ave_rise, 1.0 / n) - 1;
        return NumberOpe.controlDecimalDouble(ave_rise, 2);
    }

    @Override
    public RiskProfitIndex getRiskProfitIndex(String code) throws RemoteException, ObjectNotFoundException {
        RiskFeatureLogic riskFeatureLogic = BLController.getRiskFeatureLogic();
        RiskProfitIndex riskProfitIndex = new RiskProfitIndex();
        CalculateDataHandler calculateDataHandler = new CalculateDataHandler(code);
        Map<String, List<Double>> riseData = calculateDataHandler.setTimeType(TimeType.ONE_YEAR)
                .setBaseCode("I000300").setUnitType(UnitType.DAY).getDatasByRise();
        ConstParameterEntity constParameterEntity = baseInfoDataService.getConstParameter();
        double no_risk_profit = constParameterEntity.getNoRiskProfit() / 100;

        riskProfitIndex.code = code;

        try {
            CalculateTool calculateTool = MatlabBoot.getCalculateTool();
            List<Double> fund_info = riseData.get(code);
            List<Double> base_info = riseData.get(baseCode);
            MWNumericArray fund_info_mwn = TypeConverter.convertList(fund_info);
            MWNumericArray base_info_mwn = TypeConverter.convertList(base_info);
            double[] alphaBeta = TypeConverter.getDoubleResults(calculateTool.singleIndexModule(2, base_info_mwn,
                    fund_info_mwn, no_risk_profit, 1.0), 2);
            riskProfitIndex.alpha = alphaBeta[0] * 100;
            riskProfitIndex.beta = alphaBeta[1] * 100;
            riskProfitIndex.aveProfit = fund_info.stream().reduce(0.0, (a, b) -> (a + b))
                    / fund_info.size() * 100;
            riskProfitIndex.jensen = TypeConverter.getSingleDoubleResult(calculateTool.calJensen
                    (1, fund_info_mwn, base_info_mwn, no_risk_profit, 1.0));
            riskProfitIndex.profitSd = TypeConverter.getSingleDoubleResult(calculateTool.starndarDeviation
                    (1, fund_info_mwn)) * 100;
            riskProfitIndex.treynor = TypeConverter.getSingleDoubleResult(calculateTool.calTreynor(1,
                    fund_info_mwn, base_info_mwn, no_risk_profit, 1.0));
            riskProfitIndex.sharpe = TypeConverter.getSingleDoubleResult(calculateTool.calSharpe
                    (1, fund_info_mwn, no_risk_profit, 1.0));
            riskProfitIndex.yearWaveRate = TypeConverter.getSingleDoubleResult(calculateTool
                    .yearWaveRate(1, fund_info_mwn, 1.0)) * 100;

        } catch (NotInitialedException e) {
            e.printStackTrace();
        } catch (MWException e) {
            e.printStackTrace();
        }

        FundInfosEntity fundInfosEntity = baseInfoDataService.getFundInfo(code);
        riskProfitIndex.name = fundInfosEntity.getSimpleName();
        riskProfitIndex.investType = fundInfosEntity.getInvestType();
        riskProfitIndex.manageCompany = baseInfoDataService.getCompanyInfo(fundInfosEntity
                .getCompany()).getCompanyName();

        NumberOpe.controlDecimal(riskProfitIndex, 3);
        return riskProfitIndex;
    }

    @Override
    public InvestStyleAnalyse getInvestStyleAnalyse(String code) throws RemoteException, ObjectNotFoundException {
        InvestStyleAnalyse investStyleAnalyse = new InvestStyleAnalyse();
        FundInfosEntity fundInfosEntity = baseInfoDataService.getFundInfo(code);
        investStyleAnalyse.code = code;
        investStyleAnalyse.name = fundInfosEntity.getSimpleName();
        investStyleAnalyse.aveHoldTime = 0;
        investStyleAnalyse.holdNetWorthRate = 0;
        investStyleAnalyse.holdProfitRate = 0;
        investStyleAnalyse.investStyle = "test";
        investStyleAnalyse.manageCompany = baseInfoDataService.getCompanyInfo(fundInfosEntity
                .getCompany()).getCompanyName();
        investStyleAnalyse.topFiveIndustryRate = 0;
        investStyleAnalyse.topTenIndustryRate = 0;
        investStyleAnalyse.topTenStockRate = 0;
        investStyleAnalyse.topThreeIndustryRate = 0;
        investStyleAnalyse.investType = "test";
        return investStyleAnalyse;
    }
}
