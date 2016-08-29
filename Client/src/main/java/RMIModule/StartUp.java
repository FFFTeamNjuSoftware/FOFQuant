package RMIModule;

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
        System.out.println("success");
    }
}
