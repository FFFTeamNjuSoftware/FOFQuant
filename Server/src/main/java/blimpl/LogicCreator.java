package blimpl;

import bl.*;

/**
 * Created by Daniel on 2016/8/16.
 */
public interface LogicCreator {
    BaseInfoLogic getBaseInfoLogic();

    InvestmentPortfolioLogic getInvestmentPortfolioLogic();

    MarketLogic getMarketLogic();

    UserLogic getUserLogic();

    RiskFeatureLogic getRiskFeatureLogic();

    ProfitFeatureLogic getProfitFeatureLogic();
}
