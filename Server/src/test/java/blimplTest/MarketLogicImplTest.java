package blimplTest;

import beans.ProfitRateInfo;
import bl.MarketLogic;
import blimpl.BLController;
import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import startup.HibernateBoot;
import util.UnitType;

/**
 * MarketLogicImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>08/18/2016</pre>
 */
public class MarketLogicImplTest {
    MarketLogic marketLogic;

    @Before
    public void before() throws Exception {
        HibernateBoot.init();
        marketLogic = BLController.getMarketLogic();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getInstance()
     */
    @Test
    public void testGetInstance() throws Exception {
//TODO: Test goes here...
    }

    /**
     * Method: getPriceInfo(String code, UnitType type)
     */
    @Test
    public void testGetPriceInfoForCodeType() throws Exception {
        marketLogic.getPriceInfo("000001", UnitType.DAY).stream().forEach(e -> System.out
                .println("DAY" + new Gson().toJson(e)));
        System.out.println("————————————————————————————————————————————————————————————————");
        marketLogic.getPriceInfo("000001", UnitType.WEEK).stream().forEach(e -> System.out
                .println("WEEK" + new Gson().toJson(e)));
        System.out.println("————————————————————————————————————————————————————————————————");
        marketLogic.getPriceInfo("000001", UnitType.MONTH).stream().forEach(e -> System.out
                .println("MONTH" + new Gson().toJson(e)));
        System.out.println("————————————————————————————————————————————————————————————————");
        marketLogic.getPriceInfo("000001", UnitType.QUARTER).stream().forEach(e -> System.out
                .println("QUARTER" + new Gson().toJson(e)));
        System.out.println("————————————————————————————————————————————————————————————————");
        marketLogic.getPriceInfo("000001", UnitType.YEAR).stream().forEach(e -> System.out
                .println("YEAR" + new Gson().toJson(e)));
    }

    /**
     * Method: getPriceInfo(String code, UnitType type, int counts)
     */
    @Test
    public void testGetPriceInfoForCodeTypeCounts() throws Exception {
//TODO: Test goes here...
    }

    /**
     * Method: getPriceInfo(String code, UnitType type, String startDate, String endDate)
     */
    @Test
    public void testGetPriceInfoForCodeTypeStartDateEndDate() throws Exception {
        marketLogic.getPriceInfo("000001", UnitType.WEEK, "2016-03-01", "2016-06-01").stream()
                .forEach(e -> System.out.println("WEEK" + new Gson().toJson(e)));
        System.out.println("————————————————————————————————————————————————————————————————");
        marketLogic.getPriceInfo("000001", UnitType.WEEK, 30).stream()
                .forEach(e -> System.out.println("WEEK" + new Gson().toJson(e)));
    }

    /**
     * Method: getProfitRateInfo(String code)
     */
    @Test
    public void testGetProfitRateInfo() throws Exception {
        ProfitRateInfo info = marketLogic.getProfitRateInfo("000001");
        System.out.println(new Gson().toJson(info));
    }

    /**
     * Method: getFundProfitInfoChart(String code, UnitType type, TimeType
     * timeType, ChartType chartType)
     */
    @Test
    public void testGetFundProfitInfoChart() throws Exception {
//TODO: Test goes here... 
    }


} 
