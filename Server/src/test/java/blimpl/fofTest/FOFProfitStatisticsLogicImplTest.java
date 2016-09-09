package blimpl.fofTest;

import beans.ProfitStatisticsInfo;
import bl.fof.FOFProfitStatisticsLogic;
import blimpl.BLController;
import com.google.gson.Gson;
import org.hibernate.Hibernate;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import startup.HibernateBoot;
import util.FOFUtilInfo;
import util.UnitType;

import java.time.LocalDate;

/**
 * FOFProfitStatisticsLogicImpl Tester.
 *
 * @version 1.0
 */
public class FOFProfitStatisticsLogicImplTest {
    FOFProfitStatisticsLogic fofProfitStatisticsLogic;

    @Before
    public void before() throws Exception {
        HibernateBoot.init();
        fofProfitStatisticsLogic = BLController.getFOFPrifitStatisticsLogic();
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
     * Method: setStartDate(String startDate)
     */
    @Test
    public void testSetStartDate() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setEndDate(String endDate)
     */
    @Test
    public void testSetEndDate() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setProformanceBase(String indexCode)
     */
    @Test
    public void testSetProformanceBase() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setUnitType(UnitType unitType)
     */
    @Test
    public void testSetUnitType() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getProfitStatisticsInfo()
     */
    @Test
    public void testGetProfitStatisticsInfo() throws Exception {
        fofProfitStatisticsLogic.setStartDate("2015-01-01");
        fofProfitStatisticsLogic.setUnitType(UnitType.DAY);
        fofProfitStatisticsLogic.setEndDate(LocalDate.now().toString());
        fofProfitStatisticsLogic.setProformanceBase(FOFUtilInfo.performanceBaseInfo.get("上证基金指数"));
        ProfitStatisticsInfo profitStatisticsInfo = fofProfitStatisticsLogic
                .getProfitStatisticsInfo();
        System.out.println(new Gson().toJson(profitStatisticsInfo));
    }

    /**
     * Method: getPriceInfo()
     */
    @Test
    public void testGetPriceInfo() throws Exception {
    }


} 
