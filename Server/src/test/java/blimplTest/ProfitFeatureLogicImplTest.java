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
        RiskProfitIndex riskProfitIndex = profitFeatureLogic.getRiskProfitIndex("000001");
        System.out.println(new Gson().toJson(riskProfitIndex));
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
