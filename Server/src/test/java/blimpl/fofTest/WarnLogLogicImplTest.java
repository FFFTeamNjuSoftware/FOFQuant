package blimpl.fofTest;

import bl.fof.WarnLogLogic;
import blimpl.BLController;
import blimpl.fof.WarnLogLogicImpl;
import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import startup.HibernateBoot;

/**
 * WarnLogLogicImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>09/09/2016</pre>
 */
public class WarnLogLogicImplTest {
    WarnLogLogic warnLogLogic;

    @Before
    public void before() throws Exception {
        HibernateBoot.init();
        warnLogLogic= BLController.getWarnLogLigc();
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
     * Method: getWarnLogs()
     */
    @Test
    public void testGetWarnLogs() throws Exception {
        warnLogLogic.getWarnLogs().forEach(e->System.out.println(new Gson().toJson(e)));
    }


} 
