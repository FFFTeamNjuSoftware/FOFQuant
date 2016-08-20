package dataserviceimplTest;

import beans.CodeName;
import com.google.gson.Gson;
import dataservice.BaseInfoDataService;
import dataserviceimpl.DataServiceController;
import entities.FundInfosEntity;
import exception.ObjectNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import startup.HibernateBoot;

import java.util.List;

/**
 * BaseInfoDataServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>08/16/2016</pre>
 */
public class BaseInfoDataServiceImplTest {
    BaseInfoDataService dataService;

    @Before
    public void before() throws Exception {
        HibernateBoot.init();
        dataService = DataServiceController.getBaseInfoDataService();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getFundInfo(String code)
     */
    @Test
    public void testGetFundInfo() {
        FundInfosEntity entity = null;
        try {
            entity = dataService.getFundInfo("000005");
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(new Gson().toJson(entity));
    }

    /**
     * Method: fuzzySearch(String keyword)
     */
    @Test
    public void testFuzzySearch() throws Exception {
        List<CodeName> codeNameList;
        codeNameList = dataService.fuzzySearch("华夏");
        for (CodeName codeName : codeNameList) {
            System.out.println(new Gson().toJson(codeName));
        }
    }

    /**
     * Method: getAllCodes()
     */
    @Test
    public void testGetAllCodes() throws Exception {
        List<String> str = dataService.getAllCodes();
        str.stream().forEach(System.out::println);
    }

    /**
     * Method: getSectorCodes(String sectorId)
     */
    @Test
    public void testGetSectorCodes() throws Exception {
        List<String> str = dataService.getSectorCodes("000002");
        System.out.println(str.size());
        str.stream().forEach(System.out::println);
    }

    /**
     * Method: getSectorCodes(String sectorId)
     */
    @Test
    public void testGetCompany() throws Exception {
        System.out.println(new Gson().toJson(dataService.getCompanyInfo("00000015")));


        dataService.getAllCompanyInfos().forEach(e-> System.out.println(new Gson().toJson(e)));


        List<String> str = dataService.getCompanyFundCodes("00000015");
        System.out.println(str.size());
        str.stream().forEach(System.out::println);
    }


} 
