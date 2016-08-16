package RMIModule;

import bl.*;
import blimpl.BLController;
import config.RMIConfig;
import org.dom4j.DocumentException;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniel on 2016/8/15.
 */
public class NetInitial {
    static Map<String, Object> serviceMap;

    static {
        serviceMap = new HashMap<>();
        serviceMap.put(UserLogic.class.getSimpleName(), BLController.getUserLogic());
        serviceMap.put(BaseInfoLogic.class.getSimpleName(), BLController.getBaseInfoLogic());
        serviceMap.put(MarketLogic.class.getSimpleName(), BLController.getMarketLogic());
        serviceMap.put(InvestmentPortfolioLogic.class.getSimpleName(),
                BLController.getInvestmentPortfolioLogic());
        serviceMap.put(RiskFeatureLogic.class.getSimpleName(), BLController.getRiskFeatureLogic());
        serviceMap.put(ProfitFeatureLogic.class.getSimpleName(), BLController.getProfitFeatureLogic());
    }

    public static void initial() throws DocumentException, RemoteException, AlreadyBoundException,
            MalformedURLException {
        RMIConfig config = RMIConfig.getInstance();
        LocateRegistry.createRegistry(config.getPort());
        for (Map.Entry<String, Object> entry : serviceMap.entrySet()) {
            Naming.bind("rmi://" + config.getIpAddress() + ":" + config.getPort() + "/" +
                    entry.getKey(), (UnicastRemoteObject) entry.getValue());
        }
        System.out.println("server started!!!");
    }
}
