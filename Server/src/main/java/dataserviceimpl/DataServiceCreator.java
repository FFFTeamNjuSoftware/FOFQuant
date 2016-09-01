package dataserviceimpl;

import dataservice.*;

/**
 * Created by Daniel on 2016/8/16.
 */
public interface DataServiceCreator {
    BaseInfoDataService getBaseInfoDataService();

    MarketDataService getMarketDataService();

    InvestmentPortfolioDataService getInvestmentPortfolioDataService();

    UserDataService getUserDataService();

    IndexDataService getIndexDataService();

    FOFDataService getFOFDataService();
}
