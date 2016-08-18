package blimpl;

import bl.*;
import stubs.logic.*;

import java.rmi.RemoteException;

/**
 * Created by Daniel on 2016/8/16.
 */
public class BLController {
    private static LogicCreator trueCreator = new LogicCreator() {
        @Override
        public BaseInfoLogic getBaseInfoLogic() {
            return BaseInfoLogicImpl.getInstance();
        }

        @Override
        public InvestmentPortfolioLogic getInvestmentPortfolioLogic() {
            return InvestmentPortfolioLogicImpl.getInstance();
        }

        @Override
        public MarketLogic getMarketLogic() {
            return MarketLogicImpl.getInstance();
        }

        @Override
        public UserLogic getUserLogic() {
            return UserLogicImpl.getInstance();
        }

        @Override
        public RiskFeatureLogic getRiskFeatureLogic() {
            return RiskFeatureLogicImpl.getInstance();
        }

        @Override
        public ProfitFeatureLogic getProfitFeatureLogic() {
            return ProfitFeatureLogicImpl.getInstance();
        }
    };

    private static LogicCreator stubCreator = new LogicCreator() {
        @Override
        public BaseInfoLogic getBaseInfoLogic() {
            try {
                return new BaseInfoLogicStub();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public InvestmentPortfolioLogic getInvestmentPortfolioLogic() {
            try {
                return new InvestmentPortfolioLogicStub();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public MarketLogic getMarketLogic() {
            try {
                return new MarketLogicStub();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public UserLogic getUserLogic() {
            try {
                return new UserLogicStub();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public RiskFeatureLogic getRiskFeatureLogic() {
            try {
                return new RiskFeatureLogicStub();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public ProfitFeatureLogic getProfitFeatureLogic() {
            try {
                return new ProfitFeatureLogicStub();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return null;
        }
    };

    private static LogicCreator currentCreator;

    static {
        currentCreator = stubCreator;
//        currentCreator = trueCreator;
    }

    public static BaseInfoLogic getBaseInfoLogic() {
        return currentCreator.getBaseInfoLogic();
    }

    public static InvestmentPortfolioLogic getInvestmentPortfolioLogic() {
        return currentCreator.getInvestmentPortfolioLogic();
    }

    public static MarketLogic getMarketLogic() {
        return currentCreator.getMarketLogic();
    }

    public static UserLogic getUserLogic() {
        return currentCreator.getUserLogic();
    }

    public static RiskFeatureLogic getRiskFeatureLogic() {
        return currentCreator.getRiskFeatureLogic();
    }

    public static ProfitFeatureLogic getProfitFeatureLogic() {
        return currentCreator.getProfitFeatureLogic();
    }
}
