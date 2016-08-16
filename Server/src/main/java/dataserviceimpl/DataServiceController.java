package dataserviceimpl;

import dataservice.BaseInfoDataService;
import dataservice.InvestmentPortfolioDataService;
import dataservice.MarketDataService;
import dataservice.UserDataService;
import stubs.dataservice.BaseInfoDataServiceStub;
import stubs.dataservice.InvestmentPortfolioDataServiceStub;
import stubs.dataservice.MarketDataServiceStub;
import stubs.dataservice.UserDataServiceStub;

/**
 * Created by Daniel on 2016/8/16.
 */
public class DataServiceController {

    private static DataServiceCreator trueCreator = new DataServiceCreator() {
        @Override
        public BaseInfoDataService getBaseInfoDataService() {
            return new BaseInfoDataServiceImpl();
        }

        @Override
        public MarketDataService getMarketDataService() {
            return new MarketDataServiceImpl();
        }

        @Override
        public InvestmentPortfolioDataService getInvestmentPortfolioDataService() {
            return new InvestmentPortfolioDataServiceImpl();
        }

        @Override
        public UserDataService getUserDataService() {
            return new UserDataServiceImpl();
        }
    };
    private static DataServiceCreator stubCreator = new DataServiceCreator() {
        @Override
        public BaseInfoDataService getBaseInfoDataService() {
            return new BaseInfoDataServiceStub();
        }

        @Override
        public MarketDataService getMarketDataService() {
            return new MarketDataServiceStub();
        }

        @Override
        public InvestmentPortfolioDataService getInvestmentPortfolioDataService() {
            return new InvestmentPortfolioDataServiceStub();
        }

        @Override
        public UserDataService getUserDataService() {
            return new UserDataServiceStub();
        }
    };

    private static DataServiceCreator currentCreator;

    static {
//        currentCreator = stubCreator;
        currentCreator = trueCreator;
    }

    public static BaseInfoDataService getBaseInfoDataService() {
        return currentCreator.getBaseInfoDataService();
    }


    public static MarketDataService getMarketDataService() {
        return currentCreator.getMarketDataService();
    }

    public static InvestmentPortfolioDataService getInvestmentPortfolioDataService() {
        return currentCreator.getInvestmentPortfolioDataService();
    }

    public static UserDataService getUserDataService() {
        return currentCreator.getUserDataService();
    }
}
