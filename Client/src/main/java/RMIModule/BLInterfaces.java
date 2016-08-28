package RMIModule;

import bl.*;
import bl.fof.FOFProfitAnalyseLogic;
import config.RMIConfig;
import org.dom4j.DocumentException;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by Daniel on 2016/8/15.
 */
public class BLInterfaces {

	private static BaseInfoLogic baseInfoLogic;
	private static InvestmentPortfolioLogic investmentPortfolioLogic;
	private static MarketLogic marketLogic;
	private static ProfitFeatureLogic profitFeatureLogic;
	private static RiskFeatureLogic riskFeature;
	private static UserLogic userLogic;
	private static FOFProfitAnalyseLogic fofProfitAnalyseLogic;

	/**
	 * @throws DocumentException
	 */
	public static void netStart() throws DocumentException {
		RMIConfig config = RMIConfig.getInstance();
		String url = "rmi://" + config.getIpAddress() + ":" + config.getPort() + "/";
		try {
			baseInfoLogic = (BaseInfoLogic) Naming.lookup(url + BaseInfoLogic.class.getSimpleName());
			investmentPortfolioLogic = (InvestmentPortfolioLogic) Naming.lookup
					(url + InvestmentPortfolioLogic.class.getSimpleName());
			marketLogic = (MarketLogic) Naming.lookup(url + MarketLogic.class.getSimpleName());
			profitFeatureLogic = (ProfitFeatureLogic) Naming.lookup(url + ProfitFeatureLogic.class
					.getSimpleName());
			riskFeature = (RiskFeatureLogic) Naming.lookup(url + RiskFeatureLogic.class.getSimpleName());
			userLogic = (UserLogic) Naming.lookup(url + UserLogic.class.getSimpleName());
			fofProfitAnalyseLogic = (FOFProfitAnalyseLogic) Naming.lookup(url + FOFProfitAnalyseLogic.class.getSimpleName());
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public static BaseInfoLogic getBaseInfoLogic() {
		return baseInfoLogic;
	}

	public static InvestmentPortfolioLogic getInvestmentPortfolioLogic() {
		return investmentPortfolioLogic;
	}

	public static MarketLogic getMarketLogic() {
		return marketLogic;
	}

	public static ProfitFeatureLogic getProfitFeatureLogic() {
		return profitFeatureLogic;
	}

	public static RiskFeatureLogic getRiskFeature() {
		return riskFeature;
	}

	public static UserLogic getUserLogic() {
		return userLogic;
	}

	public static FOFProfitAnalyseLogic getFofProfitAnalyseLogic() {
		return fofProfitAnalyseLogic;
	}
}
