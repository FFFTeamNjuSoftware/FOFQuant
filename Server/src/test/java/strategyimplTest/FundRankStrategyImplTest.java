package strategyimplTest;

import org.junit.Before;
import org.junit.Test;
import startup.HibernateBoot;
import strategy.FundRankStrategy;
import strategyimpl.FundRankStrategyImpl;
import util.TimeType;

/**
 * Created by Seven on 16/8/24.
 */
public class FundRankStrategyImplTest {
    FundRankStrategy fundRankStrategy;

    @Before
    public void before() throws Exception{
        HibernateBoot.init();
        fundRankStrategy=new FundRankStrategyImpl();
    }

    @Test
    public void getFundReturnRate() throws Exception {
        double returnRate=fundRankStrategy.getFundReturnRate("000002",12,TimeType.ONE_YEAR);
        System.out.println(returnRate);
    }

    @Test
    public void getFundNoRiskRate() throws Exception {
        double noRiskRate=fundRankStrategy.getFundNoRiskRate("000002",12);
        System.out.println(noRiskRate);
    }

    @Test
    public void getFundProfit() throws Exception {
        double profit=fundRankStrategy.getFundProfit("000002",12, TimeType.ONE_YEAR);
        System.out.println(profit);
    }

    @Test
    public void getMRAR() throws Exception {
        double mrar=fundRankStrategy.getMRAR("000002",TimeType.ONE_YEAR);
        System.out.println(mrar);
    }

    @Test
    public void refreshFundRank() throws Exception {
        fundRankStrategy.refreshFundRank(TimeType.ONE_YEAR);
    }

}