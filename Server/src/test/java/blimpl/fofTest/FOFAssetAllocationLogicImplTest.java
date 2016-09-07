package blimpl.fofTest;

import bl.fof.FOFAssetAllocationLogic;
import blimpl.BLController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * FOFAssetAllocationLogicImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>09/07/2016</pre>
 */
public class FOFAssetAllocationLogicImplTest {
    FOFAssetAllocationLogic fofAssetAllocationLogic;

    @Before
    public void before() throws Exception {
        fofAssetAllocationLogic = BLController.getFOFAssetAllocationLogic();
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
     * Method: getAllSupportDate()
     */
    @Test
    public void testGetAllSupportDate() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setDate(String date)
     */
    @Test
    public void testSetDate() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getFOFPositionChanges()
     */
    @Test
    public void testGetFOFPositionChanges() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getFOFAssetAllocation()
     */
    @Test
    public void testGetFOFAssetAllocation() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getWeights()
     */
    @Test
    public void testGetWeights() throws Exception {
    }

    /**
     * Method: changePosition(Map<String, Double> newWeight)
     */
    @Test
    public void testChangePosition() throws Exception {
        Map<String, Double> newWeight = new HashMap<>();
        newWeight.put("000122", 0.1);
        newWeight.put("000131", 0.15);
        newWeight.put("050021", 0.3);
        newWeight.put("070023",0.75);
        fofAssetAllocationLogic.changePosition(newWeight);
    }


} 
