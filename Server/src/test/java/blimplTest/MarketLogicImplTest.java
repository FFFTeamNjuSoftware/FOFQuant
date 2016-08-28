package blimplTest;

import beans.PriceInfo;
import beans.ProfitChartInfo;
import beans.ProfitRateInfo;
import bl.MarketLogic;
import blimpl.BLController;
import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import startup.HibernateBoot;
import util.ChartType;
import util.TimeType;
import util.UnitType;

import java.util.List;

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
        marketLogic.getPriceInfo("I000011", UnitType.WEEK, "2016-03-01", "2016-06-01").stream()
                .forEach(e -> System.out.println("WEEK" + new Gson().toJson(e)));
        System.out.println("————————————————————————————————————————————————————————————————");
        marketLogic.getPriceInfo("I000011", UnitType.WEEK, 30).stream()
                .forEach(e -> System.out.println("WEEK" + new Gson().toJson(e)));

        List<PriceInfo> infos = marketLogic.getPriceInfo("540006", UnitType.DAY, "2016-01-01",
                "2016-08-27");
        double rate = 1;
        for (PriceInfo info : infos) {
            rate *= (1 + info.rise / 100);
            System.out.println(rate);
        }
        rate *= (1 - 0.0004) * (1 - 0.0009) * (1 - 0.0011) * (1 + 0.0001) * (1 + 0.0005);
        System.out.println(rate);
    }

    /**
     * Method: getProfitRateInfo(String code)
     */
    @Test
    public void testGetProfitRateInfo() throws Exception {
        ProfitRateInfo info = marketLogic.getProfitRateInfo("540006");
        System.out.println(new Gson().toJson(info));
    }

    /**
     * Method: getFundProfitInfoChart(String code, UnitType type, TimeType
     * timeType, ChartType chartType)
     */
    @Test
    public void testGetFundProfitInfoChart() throws Exception {
        List<ProfitChartInfo> infos = marketLogic.getFundProfitInfoChart("000001", UnitType.DAY, TimeType.ONE_MONTH,
                ChartType.MILLION_WAVE_CHART);
        infos.stream().forEach(e -> System.out.println(new Gson().toJson(e)));
    }


} 
