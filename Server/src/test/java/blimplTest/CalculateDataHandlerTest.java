package blimplTest;

import beans.PriceInfo;
import blimpl.CalculateDataHandler;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.List;
import java.util.Map;

/**
 * CalculateDataHandler Tester.
 *
 * @version 1.0
 */
public class CalculateDataHandlerTest {
    CalculateDataHandler calculateDataHandler;

    @Before
    public void before() throws Exception {
        calculateDataHandler = new CalculateDataHandler("000001");
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: setBaseCode(String... baseCode)
     */
    @Test
    public void testSetBaseCode() throws Exception {
    }

    /**
     * Method: setDate(String startDate, String endDate)
     */
    @Test
    public void testSetDate() throws Exception {
    }

    /**
     * Method: setStartDateWithoutEndDate(String startDate)
     */
    @Test
    public void testSetStartDateWithoutEndDate() throws Exception {
    }

    /**
     * Method: getDatasByPriceInfo()
     */
    @Test
    public void testGetDatasByPriceInfo() throws Exception {
//        calculateDataHandler.setStartDateWithoutEndDate("2016-07-03");
        Map<String, List<PriceInfo>> re = calculateDataHandler.getDatasByPriceInfo();
        for (String key : re.keySet()) {
            System.out.println(re.get(key).size());
            re.get(key).forEach(e -> System.out.println(new Gson().toJson(e)));
        }
    }

    /**
     * Method: getDatasByRise()
     */
    @Test
    public void testGetDatasByRise() throws Exception {
    }


} 
