package blimpl.fofTest;

import bl.fof.FOFRealTimeMonitorLogic;
import blimpl.BLController;
import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.FOFUtilInfo;

/**
 * FOFRealTimeMonitorLogicImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>09/03/2016</pre>
 */
public class FOFRealTimeMonitorLogicImplTest {
    FOFRealTimeMonitorLogic fofRealTimeMonitorLogic;

    @Before
    public void before() throws Exception {
        fofRealTimeMonitorLogic = BLController.getFOFRealTimeMonitorLogic();
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
     * Method: getFundInFOFQuickinfo()
     */
    @Test
    public void testGetFundInFOFQuickinfo() throws Exception {
        fofRealTimeMonitorLogic.setProformanceBase(FOFUtilInfo.performanceBaseInfo.get("沪深300"));
        fofRealTimeMonitorLogic.getFundInFOFQuickinfo().forEach(e->System.out.println(new Gson()
                .toJson(e)));
    }

    /**
     * Method: setProformanceBase(String indexCode)
     */
    @Test
    public void testSetProformanceBase() throws Exception {
//TODO: Test goes here... 
    }


} 
