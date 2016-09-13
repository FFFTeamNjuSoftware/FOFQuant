package strategyimplTest;

import org.junit.Before;
import org.junit.Test;
import strategy.FundDeployStrategy;
import strategyimpl.FundDeployStrategyImpl;
import util.SectorType;

/**
 * Created by Seven on 16/8/30.
 */
public class FundDeployStrategyImplTest {
    FundDeployStrategy fundDeployStrategy;

    @Before
    public void before(){
        fundDeployStrategy=new FundDeployStrategyImpl();
    }

    @Test
    public void getFundsProfit() throws Exception {

    }

    @Test
    public void getFundsFee() throws Exception {

    }

    @Test
    public void getWRpturn() throws Exception {

    }

    @Test
    public void defaultFundDeploy() throws Exception {
        fundDeployStrategy.DefaultFundDeploy(SectorType.RIGHTS_TYPE);
//        System.out.println("--------");
//        fundDeployStrategy.DefaultFundDeploy(SectorType.RIGHTS_TYPE);
    }

    @Test
    public void customizedFundDeploy() throws Exception {

    }

}