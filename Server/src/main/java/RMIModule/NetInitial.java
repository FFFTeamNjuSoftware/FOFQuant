package RMIModule;

import bl.*;
import bl.fof.*;
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
        serviceMap.put(FOFAssetAllocationLogic.class.getSimpleName(), BLController
                .getFOFAssetAllocationLogic());
        serviceMap.put(FOFBaseInfoLogic.class.getSimpleName(), BLController.getFOFBaseInfoLogic());
        serviceMap.put(FOFGenerateLogic.class.getSimpleName(), BLController.getFOFGenerateLogic());
        serviceMap.put(FOFPerformanceAttributionLogic.class.getSimpleName(), BLController
                .getFOFPerformanceAttributionLogic());
        serviceMap.put(FOFProfitAnalyseLogic.class.getSimpleName(), BLController
                .getFOFProfitAnalyseLogic());
        serviceMap.put(FOFProfitStatisticsLogic.class.getSimpleName(), BLController
                .getFOFPrifitStatisticsLogic());
        serviceMap.put(FOFRealTimeMonitorLogic.class.getSimpleName(), BLController
                .getFOFRealTimeMonitorLogic());
        serviceMap.put(WarnLogLogic.class.getSimpleName(),BLController.getWarnLogLigc());
    }

    public static void initial() throws DocumentException, RemoteException, AlreadyBoundException,
            MalformedURLException {
        RMIConfig config = RMIConfig.getInstance();
        LocateRegistry.createRegistry(config.getPort());
        for (Map.Entry<String, Object> entry : serviceMap.entrySet()) {
            Naming.bind("rmi://" + config.getIpAddress() + ":" + config.getPort() + "/" +
                    entry.getKey(), (UnicastRemoteObject) entry.getValue());
//            System.out.println(entry.getKey()+","+entry.getValue().getClass());
        }
        System.out.println("server started!!!");
    }
}
