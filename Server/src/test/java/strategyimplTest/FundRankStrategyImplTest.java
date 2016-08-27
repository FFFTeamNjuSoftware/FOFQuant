package strategyimplTest;

import beans.PriceInfo;
import bl.MarketLogic;
import blimpl.BLController;
import org.junit.Before;
import org.junit.Test;
import startup.HibernateBoot;
import strategy.FundRankStrategy;
import strategyimpl.FundRankStrategyImpl;
import util.TimeType;
import util.UnitType;

import java.util.List;
import java.util.Map;

/**
 * Created by Seven on 16/8/24.
 */
public class FundRankStrategyImplTest {
    FundRankStrategy fundRankStrategy;
    MarketLogic marketLogic;
    @Before
    public void before() throws Exception{
        HibernateBoot.init();
        fundRankStrategy=new FundRankStrategyImpl();
        marketLogic= BLController.getMarketLogic();
    }

    @Test
    public void getFundReturnRate() throws Exception {
        List<PriceInfo> priceInfoList=marketLogic.getPriceInfo("233009", UnitType.MONTH,"0000-00-00","2016-08-12");

        double returnRate=fundRankStrategy.getFundReturnRate(priceInfoList,12,TimeType.ONE_YEAR);
        System.out.println(returnRate);
    }

    @Test
    public void getFundNoRiskRate() throws Exception {
//        double noRiskRate=fundRankStrategy.getFundNoRiskRate("000001",12);
//        System.out.println(noRiskRate);
    }

    @Test
    public void getFundProfit() throws Exception {
//        double profit=fundRankStrategy.getFundProfit("000001",12, TimeType.ONE_YEAR);
//        System.out.println(profit);
    }

    @Test
    public void getMRAR() throws Exception {
        double mrar=fundRankStrategy.getMRAR("000001",TimeType.ONE_YEAR,"2015-12-31");
        System.out.println(mrar);
    }

    @Test
    public void refreshFundRank() throws Exception {
        Map<String,Integer> maps=fundRankStrategy.refreshFundRank(TimeType.THREE_YEAR);
        for(String str:maps.keySet() ){
            System.out.println(str+","+maps.get(str));
        };
    }

    @Test
    public void getFundRankByDate() throws Exception{
        Map<String,Integer> maps=fundRankStrategy.getFundRankByDate(TimeType.THREE_YEAR,
                "2016-08-26");
        for(String str:maps.keySet() ){
            System.out.println(str+","+maps.get(str));
        };
    }
}