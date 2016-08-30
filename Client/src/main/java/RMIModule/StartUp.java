package RMIModule;

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
        double alpha = BLInterfaces.getProfitFeatureLogic().getAlpha("000001");
        double beta = BLInterfaces.getRiskFeature().getBeta("000001");
        double sd = BLInterfaces.getRiskFeature().getStandardDeviation("000001");
        double sharpe = BLInterfaces.getRiskFeature().getSharpe("000001");
        System.out.println(LocalTime.now());
        System.out.println(sd);
        System.out.println(sharpe);
        System.out.println(alpha + "," + beta);
        System.out.println("success");
    }
}
