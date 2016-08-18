package dataserviceimplTest;

import com.google.gson.Gson;
import dataservice.IndexDataService;
import dataserviceimpl.DataServiceController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import startup.HibernateBoot;

/**
 * IndexDataServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>08/18/2016</pre>
 */
public class IndexDataServiceImplTest {
    IndexDataService indexDataService;

    @Before
    public void before() throws Exception {
        HibernateBoot.init();
        indexDataService = DataServiceController.getIndexDataService();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getIndexPriceInfo(String code)
     */
    @Test
    public void testGetIndexPriceInfo() throws Exception {
        indexDataService.getIndexPriceInfo("000011").stream().forEach(e->System.out.println(new
                Gson().toJson(e)));
    }


} 
