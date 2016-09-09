package blimplTest;

import beans.FundInfo;
import beans.FundQuickInfo;
import bl.BaseInfoLogic;
import blimpl.BLController;
import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import startup.HibernateBoot;

import java.util.List;

/**
 * BaseInfoLogicImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>08/20/2016</pre>
 */
public class BaseInfoLogicImplTest {
    BaseInfoLogic baseInfoLogic;

    @Before
    public void before() throws Exception {
        HibernateBoot.init();
        baseInfoLogic = BLController.getBaseInfoLogic();
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
     * Method: getFundCodes()
     */
    @Test
    public void testGetFundCodes() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: fuzzySearch(String keyword)
     */
    @Test
    public void testFuzzySearch() throws Exception {
        baseInfoLogic.fuzzySearch("大数据").forEach(e->System.out.println(new Gson().toJson(e)));
    }

    /**
     * Method: getFundBaseInfo(String code)
     */
    @Test
    public void testGetFundBaseInfo() throws Exception {
        List<String> strs = baseInfoLogic.getFundCodes();
        for (String str : strs) {
            FundInfo fundInfo = baseInfoLogic.getFundBaseInfo(str);
            System.out.println(new Gson().toJson(fundInfo));
        }
    }

    /**
     * Method: getFundQuickInfo(String sectorId)
     */
    @Test
    public void testGetFundQuickInfo() throws Exception {
        List<FundQuickInfo> infos = baseInfoLogic.getFundQuickInfo("000011");
//        System.out.println(new Gson().toJson(infos));
        infos.stream().forEach(e -> System.out.println(new Gson().toJson(e)));
    }


} 
