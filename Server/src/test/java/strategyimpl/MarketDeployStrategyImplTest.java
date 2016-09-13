package strategyimpl;

import beans.CPPIMarketDeploy;
import beans.RiskyParityDeploy;
import org.junit.Before;
import org.junit.Test;
import strategy.MarketDeployStrategy;

/**
 * Created by Seven on 16/9/13.
 */
public class MarketDeployStrategyImplTest {
    MarketDeployStrategy marketDeployStrategy;

    @Before
    public void setUp() throws Exception {
        marketDeployStrategy=new MarketDeployStrategyImpl();
    }

    @Test
    public void defaultCPPIDeploy() throws Exception {
        CPPIMarketDeploy cppiMarketDeployEntity=marketDeployStrategy.DefaultCPPIDeploy(100,2.5,0.95);
        System.out.println(cppiMarketDeployEntity.profit);
    }

    @Test
    public void customizedCPPIDeploy() throws Exception {

    }

    @Test
    public void defaultRiskyParityDeploy() throws Exception {
        RiskyParityDeploy riskyParityDeploy=marketDeployStrategy.DefaultRiskyParityDeploy();
        System.out.println(riskyParityDeploy.rpturn);

    }

    @Test
    public void customizedRiskyParityDeploy() throws Exception {

    }

}