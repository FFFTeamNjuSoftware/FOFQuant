package blimpl.fofTest;

import bl.fof.FOFPerformanceAttributionLogic;
import blimpl.BLController;
import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.FOFUtilInfo;

/**
 * FOFPerformanceAttributionLogicImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>09/03/2016</pre>
 */
public class FOFPerformanceAttributionLogicImplTest {
    FOFPerformanceAttributionLogic fofPerformanceAttributionLogic;

    @Before
    public void before() throws Exception {
        fofPerformanceAttributionLogic = BLController.getFOFPerformanceAttributionLogic();
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
     * Method: getPerformanceAttribution()
     */
    @Test
    public void testGetPerformanceAttribution() throws Exception {
        fofPerformanceAttributionLogic.setStartDate("2015-01-01");
        fofPerformanceAttributionLogic.setEndDate("2015-12-31");
        fofPerformanceAttributionLogic.setProformanceBase(FOFUtilInfo.performanceBaseInfo.get
                ("沪深300"));
        fofPerformanceAttributionLogic.getPerformanceAttribution().forEach(e->System.out.println
                (new Gson().toJson(e)));
    }


} 
