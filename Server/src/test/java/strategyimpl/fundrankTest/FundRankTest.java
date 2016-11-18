package strategyimpl.fundrankTest;

import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import startup.HibernateBoot;
import strategyimpl.fundrank.FundRank;

import java.util.List;
import java.util.Map;

/**
 * FundRank Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>11/15/2016</pre>
 */
public class FundRankTest {
    FundRank fundRank;

    @Before
    public void before() throws Exception {
        HibernateBoot.init();
        fundRank=new FundRank();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getTotalFundPool()
     */
    @Test
    public void testGetTotalFundPool() throws Exception {
        Map<String,List<String>> map=fundRank.getTotalFundPool();
        for(String key:map.keySet()){
            System.out.println(key+","+map.get(key).size()+","+new Gson().toJson(map.get(key)));
        }
    }

    /**
     * Method: getProfitAbility(String code, SectorType sectorType)
     */
    @Test
    public void testGetProfitAbility() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getRiskAbility(String code, SectorType sectorType)
     */
    @Test
    public void testGetRiskAbility() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getStablity(String code, SectorType sectorType)
     */
    @Test
    public void testGetStablity() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: main(String[] args)
     */
    @Test
    public void testMain() throws Exception {
//TODO: Test goes here... 
    }


} 
