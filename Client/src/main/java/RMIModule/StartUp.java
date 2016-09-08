package RMIModule;

import com.google.gson.Gson;

import java.time.LocalTime;

/**
 * Created by Daniel on 2016/8/15.
 */
public class StartUp {
    public static void main(String[] args) throws Exception {
        BLInterfaces.netStart();
//                BLInterfaces.getUserLogic().loginIn("yyf", "123456");
//                BLInterfaces.getBaseInfoLogic().getFundQuickInfo(SectorType.STOCK_TYPE).stream()
//                        .forEach(e -> System.out.println(new Gson().toJson(e)));
//                BLInterfaces.getMarketLogic().getPriceInfo("000001", UnitType.WEEK, 16).stream()
//                        .forEach(e -> System.out.println(new Gson().toJson(e)));
//                FOFProfitAnalyse fofProfitAnalyse = BLInterfaces.getFofProfitAnalyseLogic().getFOFProfitAnalyse
//                        (TimeType.ONE_MONTH);
//                System.out.println(new Gson().toJson(fofProfitAnalyse));
        System.out.println(LocalTime.now());
        System.out.println(new Gson().toJson(BLInterfaces.getProfitFeatureLogic()
                .getRiskProfitIndex("000122")));
//        System.out.println(new Gson().toJson(BLInterfaces.getFofGenerateLogic()
//                .getLargeClassConfiguration()));
//        System.out.println(new Gson().toJson(BLInterfaces.getFofGenerateLogic()
//                .getSmallClassConfiguration()));
//        System.out.println(new Gson().toJson(BLInterfaces.getFofGenerateLogic()
//                .getTestValues()));
//        double alpha = BLInterfaces.getProfitFeatureLogic().getAlpha("000001");
//        double beta = BLInterfaces.getRiskFeature().getBeta("000001");
//        double sd = BLInterfaces.getRiskFeature().getStandardDeviation("000001");
//        double sharpe = BLInterfaces.getRiskFeature().getSharpe("000001");
//        FOFProfitAnalyseLogic fofProfitAnalyseLogic=BLInterfaces.getFofProfitAnalyseLogic();
//        fofProfitAnalyseLogic.setProformanceBase(FOFUtilInfo.performanceBaseInfo.get("沪深300"));
//        FOFProfitAnalyse fofProfitAnalyse = fofProfitAnalyseLogic.getFOFProfitAnalyse(TimeType.THREE_MONTH);
//        System.out.println(LocalTime.now());
//        System.out.println(sd);
//        System.out.println(sharpe);
//        System.out.println(alpha + "," + beta);
//        System.out.println(new Gson().toJson(fofProfitAnalyse));
        System.out.println("success");
    }
}
