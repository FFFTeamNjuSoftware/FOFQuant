package blimpl.fofTest;

import bl.fof.FOFBaseInfoLogic;
import blimpl.BLController;
import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import startup.HibernateBoot;

/**
 * FOFBaseInfoLogicImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>09/03/2016</pre>
 */
public class FOFBaseInfoLogicImplTest {
    FOFBaseInfoLogic fofBaseInfoLogic;

    @Before
    public void before() throws Exception {
        fofBaseInfoLogic = BLController.getFOFBaseInfoLogic();
        HibernateBoot.init();
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
     * Method: getFOFHistoryInfo()
     */
    @Test
    public void testGetFOFHistoryInfo() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getFundCodeInFOF()
     */
    @Test
    public void testGetFundCodeInFOF() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getFOFQuickInfo()
     */
    @Test
    public void testGetFOFQuickInfo() throws Exception {
        System.out.println(new Gson().toJson(fofBaseInfoLogic.getFOFQuickInfo()));
    }

    /**
     * Method: getFOFQuickInfo(String code)
     */
    @Test
    public void testGetFOFQuickInfoCode() throws Exception {
    }

    /**
     * Method: getFOFPriceInfos()
     */
    @Test
    public void testGetFOFPriceInfos() throws Exception {
        fofBaseInfoLogic.getFOFPriceInfos().forEach(e -> System.out.println(new Gson().toJson(e)));
    }


} 
