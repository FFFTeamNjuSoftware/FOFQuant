package blimpl.fofTest;

import beans.RiskParameters;
import bl.fof.FOFGenerateLogic;
import blimpl.BLController;
import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import startup.HibernateBoot;
import startup.MatlabBoot;
import util.StrategyType;

/**
 * FOFGenerateLogicImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>09/14/2016</pre>
 */
public class FOFGenerateLogicImplTest {
    FOFGenerateLogic fofGenerateLogic;

    @Before
    public void before() throws Exception {
        MatlabBoot.init();
        HibernateBoot.init();
        fofGenerateLogic = BLController.getFOFGenerateLogic();
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
     * Method: setTotalAsset(int assetValue)
     */
    @Test
    public void testSetTotalAsset() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setStrategyType(StrategyType strategyType)
     */
    @Test
    public void testSetStrategyType() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setRiskLevel(RiskParameters riskLevel)
     */
    @Test
    public void testSetRiskLevel() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getLargeClassConfiguration()
     */
    @Test
    public void testGetLargeClassConfiguration() throws Exception {
        fofGenerateLogic.setTotalAsset(10000000000.0);
        fofGenerateLogic.setStrategyType(StrategyType.MARKET_RISKY_PARITY);
//        System.out.println(new Gson().toJson(fofGenerateLogic.getLargeClassConfiguration()));
        fofGenerateLogic.setStrategyType(StrategyType.CPPI);
        fofGenerateLogic.setRiskLevel(RiskParameters.LOW_RISK);
        System.out.println(new Gson().toJson(fofGenerateLogic.getLargeClassConfiguration()));
//TODO: Test goes here... 
    }

    /**
     * Method: getSmallClassConfiguration()
     */
    @Test
    public void testGetSmallClassConfiguration() throws Exception {
        fofGenerateLogic.setTotalAsset(10000000000.0);
        fofGenerateLogic.setStrategyType(StrategyType.MARKET_RISKY_PARITY);
        fofGenerateLogic.getLargeClassConfiguration();
        System.out.println(new Gson().toJson(fofGenerateLogic.getSmallClassConfiguration()));
    }

    /**
     * Method: getTestValues(String startDate, String endDate)
     */
    @Test
    public void testGetTestValues() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setFOFName(String name)
     */
    @Test
    public void testSetFOFName() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setFOFCode(String code)
     */
    @Test
    public void testSetFOFCode() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: saveResult()
     */
    @Test
    public void testSaveResult() throws Exception {
//TODO: Test goes here... 
    }


} 
