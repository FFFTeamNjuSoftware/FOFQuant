package RMIModule;

import bl.*;
import blimpl.*;
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
        serviceMap.put(UserLogic.class.getSimpleName(), UserLogicImpl.getInstance());
        serviceMap.put(BaseInfoLogic.class.getSimpleName(), BaseInfoLogicImpl.getInstance());
        serviceMap.put(MarketLogic.class.getSimpleName(), MarketLogicImpl.getInstance());
        serviceMap.put(InvestmentPortfolioLogic.class.getSimpleName(),
                InvestmentPortfolioLogicImpl.getInstance());
        serviceMap.put(RiskFeatureLogic.class.getSimpleName(), RiskFeatureLogicImpl.getInstance());
        serviceMap.put(ProfitFeatureLogic.class.getSimpleName(), ProfitFeatureLogicImpl
                .getInstance());
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
