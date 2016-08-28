package blimpl;

import bl.*;
import bl.fof.*;
import blimpl.fof.FOFLogicCreator;
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
    private static FOFLogicCreator currentFOFCreator;

    static {
        //    currentCreator = stubCreator;
        currentCreator = trueCreator;
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

    public static FOFAssetAllocationLogic getFOFAssetAllocationLogic() {
        return currentFOFCreator.getFofAssetAllocationLogic();
    }

    public static FOFBaseInfoLogic getFOFBaseInfoLogic() {
        return currentFOFCreator.getFOFBaseInfoLogic();
    }

    public static FOFGenerateLogic getFOFGenerateLogic() {
        return currentFOFCreator.getFOFGenerateLogic();
    }

    public static FOFPerformanceAttributionLogic getFOFPerformanceAttributionLogic() {
        return currentFOFCreator.getFOFPerformanceAttributionLogic();
    }

    public static FOFProfitAnalyseLogic getFOFProfitAnalyseLogic() {
        return currentFOFCreator.getFOFProfitAnalyseLogic();
    }

    public static FOFProfitStatisticsLogic getFOFPrifitStatisticsLogic() {
        return currentFOFCreator.getFOFPrifitStatisticsLogic();
    }

    public static FOFRealTimeMonitorLogic getFOFRealTimeMonitorLogic() {
        return currentFOFCreator.getFOFRealTimeMonitorLogic();
    }
}
