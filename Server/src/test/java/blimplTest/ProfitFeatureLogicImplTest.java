package blimplTest;

import bl.ProfitFeatureLogic;
import blimpl.BLController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import startup.HibernateBoot;

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
//TODO: Test goes here... 
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
//TODO: Test goes here... 
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
