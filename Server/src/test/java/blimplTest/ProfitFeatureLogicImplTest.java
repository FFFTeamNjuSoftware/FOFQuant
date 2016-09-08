package blimplTest;

import beans.RiskProfitIndex;
import bl.ProfitFeatureLogic;
import blimpl.BLController;
import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import startup.HibernateBoot;
import startup.MatlabBoot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

/**
 * ProfitFeatureLogicImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>08/18/2016</pre>
 */
public class ProfitFeatureLogicImplTest {
    ProfitFeatureLogic profitFeatureLogic;

    @Before
    public void before() throws Exception {
        MatlabBoot.init();
        HibernateBoot.init();
        profitFeatureLogic = BLController.getProfitFeatureLogic();
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
     * Method: getAlpha(String code)
     */
    @Test
    public void testGetAlpha() throws Exception {

        System.out.println(profitFeatureLogic.getAlpha("000001"));
    }

    /**
     * Method: aveProfitRate(String code)
     */
    @Test
    public void testAveProfitRate() throws Exception {
        double ave = profitFeatureLogic.aveProfitRate("000001");
        System.out.println(ave);
    }

    /**
     * Method: riskProfitRate(String code)
     */
    @Test
    public void testRiskProfitRate() throws Exception {
        List<String> strs = Arrays.asList("000001","000122", "000131", "092002", "166008",
                "233005");
        for (String str : strs) {
            System.out.println(LocalTime.now());
            RiskProfitIndex riskProfitIndex = profitFeatureLogic.getRiskProfitIndex(str);
            System.out.println(new Gson().toJson(riskProfitIndex));
            System.out.println(LocalTime.now());
        }
    }

    /**
     * Method: getEnsembleAveProfitRate(String code)
     */
    @Test
    public void testGetEnsembleAveProfitRate() throws Exception {
        double ave = profitFeatureLogic.getEnsembleAveProfitRate("000001");
        System.out.println(ave);
    }


}
