package blimpl;

import bl.*;

/**
 * Created by Daniel on 2016/8/16.
 */
public interface LogicCreator {
    public BaseInfoLogic getBaseInfoLogic();

    public InvestmentPortfolioLogic getInvestmentPortfolioLogic();

    public MarketLogic getMarketLogic();

    public UserLogic getUserLogic();

    public RiskFeatureLogic getRiskFeatureLogic();

    public ProfitFeatureLogic getProfitFeatureLogic();
}
