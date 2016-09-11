package blimpl.fofTest;

import bl.fof.FOFBaseInfoLogic;
import blimpl.BLController;
import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import startup.HibernateBoot;

import java.util.List;
import java.util.Map;

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
        fofBaseInfoLogic.getFOFHistoryInfo().forEach(e -> System.out.println(new Gson().toJson(e)));
    }

    /**
     * Method: getFundCodeInFOF()
     */
    @Test
    public void testGetFundCodeInFOF() throws Exception {
        Map<String, List<String>> infos = fofBaseInfoLogic.getFundCodeInFOF();
        for (String str : infos.keySet()) {
            System.out.println("Sector " + str + ":");
            infos.get(str).forEach(System.out::println);
        }
//        infos.get("000011").forEach(System.out::println);
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
        Map<String, Map<String, Double>> infos = fofBaseInfoLogic.getNewestWeight();
        for(String key:infos.keySet()){
            for(String key1:infos.get(key).keySet()){
                System.out.println(key1+","+infos.get(key).get(key1));
            }
        }
    }

    /**
     * Method: getFOFPriceInfos()
     */
    @Test
    public void testGetFOFPriceInfos() throws Exception {
        fofBaseInfoLogic.getFOFPriceInfos().forEach(e -> System.out.println(new Gson().toJson(e)));
    }


} 
