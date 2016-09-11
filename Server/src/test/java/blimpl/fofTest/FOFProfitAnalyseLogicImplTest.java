package blimpl.fofTest;

import beans.FOFProfitAnalyse;
import bl.fof.FOFProfitAnalyseLogic;
import blimpl.BLController;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import startup.HibernateBoot;
import startup.MatlabBoot;
import util.TimeType;

/**
 * FOFProfitAnalyseLogicImpl Tester.
 *
 * @version 1.0
 */
public class FOFProfitAnalyseLogicImplTest {
    FOFProfitAnalyseLogic fofProfitAnalyseLogic;

    @Before
    public void before() throws Exception {
        HibernateBoot.init();
        MatlabBoot.init();
        fofProfitAnalyseLogic = BLController.getFOFProfitAnalyseLogic();
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
     * Method: getFOFProfitAnalyse(TimeType timeType)
     */
    @Test
    public void testGetFOFProfitAnalyse() throws Exception {
        FOFProfitAnalyse fofProfitAnalyse = fofProfitAnalyseLogic.getFOFProfitAnalyse(TimeType
                .ONE_YEAR);
        System.out.println(new Gson().toJson(fofProfitAnalyse));
    }


} 
