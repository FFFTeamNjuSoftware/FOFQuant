package dataserviceimplTest;

import beans.AssetAllocation;
import com.google.gson.Gson;
import dataservice.InvestmentPortfolioDataService;
import dataserviceimpl.DataServiceController;
import entities.AssetAllocationEntity;
import entities.BondHoldInfoEntity;
import entities.IndustryHoldInfoEntity;
import entities.StockHoldInfoEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import startup.HibernateBoot;

import java.util.List;

/**
 * InvestmentPortfolioDataServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>08/16/2016</pre>
 */
public class InvestmentPortfolioDataServiceImplTest {
    InvestmentPortfolioDataService dataService;

    @Before
    public void before() throws Exception {
        HibernateBoot.init();
        dataService = DataServiceController.getInvestmentPortfolioDataService();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getBondHoldInfo(String code)
     */
    @Test
    public void testGetBondHoldInfo() throws Exception {
        List<BondHoldInfoEntity> entities;
        entities = dataService.getBondHoldInfo("000001");
        for (BondHoldInfoEntity entity : entities) {
            System.out.println(new Gson().toJson(entity));
        }
    }

    /**
     * Method: getStockHoldInfo(String code)
     */
    @Test
    public void testGetStockHoldInfo() throws Exception {
        List<StockHoldInfoEntity> entities;
        entities = dataService.getStockHoldInfo("000001");
        for (StockHoldInfoEntity entity : entities) {
            System.out.println(new Gson().toJson(entity));
        }
    }

    /**
     * Method: getIndustryHold(String code)
     */
    @Test
    public void testGetIndustryHold() throws Exception {
        List<IndustryHoldInfoEntity> entities;
        entities = dataService.getIndustryHold("000001");
        for (IndustryHoldInfoEntity entity : entities) {
            System.out.println(new Gson().toJson(entity));
        }
    }

    /**
     * Method: getAssetAllocations(String code)
     */
    @Test
    public void testGetAssetAllocations() throws Exception {
        List<AssetAllocationEntity> entities;
        entities = dataService.getAssetAllocations("000001");
        for (AssetAllocationEntity entity : entities) {
            System.out.println(new Gson().toJson(entity));
        }
    }


} 
