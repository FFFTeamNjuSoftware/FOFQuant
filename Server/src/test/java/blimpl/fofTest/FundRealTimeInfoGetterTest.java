package blimpl.fofTest;

import bl.BaseInfoLogic;
import blimpl.BLController;
import dataupdate.FundRealTimeInfoGetter;
import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * FundRealTimeInfoGetter Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>08/30/2016</pre>
 */
public class FundRealTimeInfoGetterTest {
    FundRealTimeInfoGetter fundRealTimeInfoGetter;
    BaseInfoLogic baseInfoLogic;


    @Before
    public void before() throws Exception {
        fundRealTimeInfoGetter = new FundRealTimeInfoGetter();
        baseInfoLogic = BLController.getBaseInfoLogic();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getFundRealTimeInfo(String code)
     */
    @Test
    public void testGetFundRealTimeInfo() throws Exception {
        for (String str : baseInfoLogic.getFundCodes()) {
            System.out.println(new Gson().toJson(fundRealTimeInfoGetter.getFundRealTimeInfo(str)));
        }
    }


} 
