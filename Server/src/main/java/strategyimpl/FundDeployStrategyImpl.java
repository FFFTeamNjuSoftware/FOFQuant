package strategyimpl;

import strategy.FundDeployStrategy;

import java.util.List;

/**
 * Created by Seven on 16/8/30.
 */
public class FundDeployStrategyImpl implements FundDeployStrategy {
    @Override
    public double[][] getFundsProfit(List<String> funds, String startDate, String endDate) {
        return new double[0][];
    }

    @Override
    public double[][] getFundsFee(List<String> funds) {
        return new double[0][];
    }

    @Override
    public List getWRpturn(List<String> funds, String startDate, String endDate) {
        return null;
    }

    @Override
    public List DefaultFundDeploy() {
        //读取系统中前N只固定收益型股票的
        return null;
    }

    @Override
    public List CustomizedFundDeploy(List<String> funds) {
        return null;
    }
}
