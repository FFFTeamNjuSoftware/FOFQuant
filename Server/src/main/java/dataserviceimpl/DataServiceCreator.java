package dataserviceimpl;

import dataservice.BaseInfoDataService;
import dataservice.InvestmentPortfolioDataService;
import dataservice.MarketDataService;
import dataservice.UserDataService;

/**
 * Created by Daniel on 2016/8/16.
 */
public interface DataServiceCreator {
    public BaseInfoDataService getBaseInfoDataService();

    public MarketDataService getMarketDataService();

    public InvestmentPortfolioDataService getInvestmentPortfolioDataService();

    public UserDataService getUserDataService();
}
