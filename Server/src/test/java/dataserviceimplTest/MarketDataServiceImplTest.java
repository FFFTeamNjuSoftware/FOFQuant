package dataserviceimplTest;

import com.google.gson.Gson;
import dataservice.MarketDataService;
import dataserviceimpl.DataServiceController;
import entities.NetWorthEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import startup.HibernateBoot;

import java.util.List;

/**
 * MarketDataServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>08/16/2016</pre>
 */
public class MarketDataServiceImplTest {
    MarketDataService marketDataService;

    @Before
    public void before() throws Exception {
        HibernateBoot.init();
        marketDataService = DataServiceController.getMarketDataService();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getNetWorth(String code)
     */
    @Test
    public void testGetNetWorthCode() throws Exception {
        List<NetWorthEntity> entityList;
        entityList = marketDataService.getNetWorth("000001");
        for (NetWorthEntity entity : entityList) {
            System.out.println(new Gson().toJson(entity));
        }
    }

    /**
     * Method: getNetWorth(String code, String startDate, String endDate)
     */
    @Test
    public void testGetNetWorthForCodeStartDateEndDate() throws Exception {
        List<NetWorthEntity> entityList;
        entityList = marketDataService.getNetWorth("000001", "2016-03-01", "2016-06-01");
        for (NetWorthEntity entity : entityList) {
            System.out.println(new Gson().toJson(entity));
        }
    }


} 
