package stubs.logic.fof;

import bl.fof.FOFGenerateLogic;
import util.SectorType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniel on 2016/8/28.
 */
public class FOFGenerateLogicStub extends UnicastRemoteObject implements FOFGenerateLogic {
    public FOFGenerateLogicStub() throws RemoteException {

    }

    @Override
    public Map<String, Double> getLargeClassConfiguration() throws RemoteException {
        Map<String, Double> configuration = new HashMap<>();
        configuration.put(SectorType.STOCK_TYPE, 40.0);
        configuration.put(SectorType.ETF_TYPE, 30.0);
        configuration.put(SectorType.BOND_TYPE, 30.0);
        return configuration;
    }

    @Override
    public Map<String, Map<String, Double>> getSmallClassConfiguration() throws RemoteException {
        return null;
    }
}
