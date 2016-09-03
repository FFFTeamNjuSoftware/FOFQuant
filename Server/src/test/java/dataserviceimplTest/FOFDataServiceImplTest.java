package dataserviceimplTest;

import com.google.gson.Gson;
import dataservice.FOFDataService;
import dataserviceimpl.DataServiceController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.FOFUtilInfo;

/**
 * FOFDataServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>09/03/2016</pre>
 */
public class FOFDataServiceImplTest {
    FOFDataService fofDataService;

    @Before
    public void before() throws Exception {
        fofDataService = DataServiceController.getFOFDataService();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getFOFAssetAllocation(String fof_code)
     */
    @Test
    public void testGetFOFAssetAllocation() throws Exception {
    }

    /**
     * Method: getFofInfoEntity(String fof_code)
     */
    @Test
    public void testGetFofInfoEntity() throws Exception {
        System.out.println("FOFINFO:" + new Gson().toJson(fofDataService.getFofInfoEntity
                (FOFUtilInfo.FOF_CODE)));
    }

    /**
     * Method: getFofHoldInfos(String fof_code)
     */
    @Test
    public void testGetFofHoldInfosFof_code() throws Exception {
        System.out.println("HoldInfos:" + new Gson().toJson(fofDataService.getFofHoldInfos(FOFUtilInfo.FOF_CODE)));
    }

    /**
     * Method: getFofHoldInfos(String fof_code, String startDate, String endDate)
     */
    @Test
    public void testGetFofHoldInfosForFof_codeStartDateEndDate() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getNewestFofHoldInfos(String fof_code)
     */
    @Test
    public void testGetNewestFofHoldInfos() throws Exception {
        System.out.println("NewestFofHoldInfo" + new Gson().toJson(fofDataService
                .getNewestFofHoldInfos(FOFUtilInfo.FOF_CODE)));
    }

    /**
     * Method: getPositionChange(String fof_code)
     */
    @Test
    public void testGetPositionChange() throws Exception {
    }

    /**
     * Method: getPositionChangeRequest(String fof_code)
     */
    @Test
    public void testGetPositionChangeRequest() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getFofHistoryInfos(String fof_code)
     */
    @Test
    public void testGetFofHistoryInfos() throws Exception {
        System.out.println("HistoryInfo:" + new Gson().toJson(fofDataService.getFofHistoryInfos
                (FOFUtilInfo.FOF_CODE)));
    }

    /**
     * Method: getNewestHistoryInfo(String fof_code)
     */
    @Test
    public void testGetNewestHistoryInfo() throws Exception {
        System.out.println("NewestHistoryInfo" + new Gson().toJson(fofDataService.getNewestHistoryInfo
                (FOFUtilInfo.FOF_CODE)));
    }

    /**
     * Method: getFofEstablishInfo(String fof_code)
     */
    @Test
    public void testGetFofEstablishInfo() throws Exception {
        System.out.println("EstablishInfo:" + new Gson().toJson(fofDataService.getFofEstablishInfo
                (FOFUtilInfo
                        .FOF_CODE)));
    }


} 
