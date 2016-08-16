package blimplTest;

import beans.AssetAllocation;
import bl.InvestmentPortfolioLogic;
import blimpl.BLController;
import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import startup.HibernateBoot;
import util.HoldingType;

import java.util.List;

/**
 * InvestmentPortfolioLogicImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>08/16/2016</pre>
 */
public class InvestmentPortfolioLogicImplTest {
    InvestmentPortfolioLogic investmentPortfolioLogic;

    @Before
    public void before() throws Exception {
        HibernateBoot.init();
        investmentPortfolioLogic = BLController.getInvestmentPortfolioLogic();
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
     * Method: getAssetAllocation(String code)
     */
    @Test
    public void testGetAssetAllocation() throws Exception {
        List<AssetAllocation> assetAllocations = investmentPortfolioLogic.getAssetAllocation
                ("000001");
        assetAllocations.stream().forEach(e -> System.out.println(new Gson().toJson(e)));
    }

    /**
     * Method: getHoldingInfos(String code, HoldingType type)
     */
    @Test
    public void testGetHoldingInfos() throws Exception {
        investmentPortfolioLogic.getHoldingInfos("000001", HoldingType.BOND).stream().forEach
                (e -> System.out.println(new Gson().toJson(e)));
        System.out.println("————————————————————————————————————————————————————————————————————");
        investmentPortfolioLogic.getHoldingInfos("000001", HoldingType.STOCK).stream().forEach
                (e -> System.out.println(new Gson().toJson(e)));
        System.out.println("————————————————————————————————————————————————————————————————————");
        investmentPortfolioLogic.getHoldingInfos("000001", HoldingType.INDUSTRY).stream().forEach
                (e -> System.out.println(new Gson().toJson(e)));
    }


} 
