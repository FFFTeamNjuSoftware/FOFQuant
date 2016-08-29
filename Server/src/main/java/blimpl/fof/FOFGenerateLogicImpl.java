package blimpl.fof;

import bl.fof.FOFGenerateLogic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

/**
 * Created by Daniel on 2016/8/26.
 */
public class FOFGenerateLogicImpl extends UnicastRemoteObject implements FOFGenerateLogic {
    private FOFGenerateLogicImpl() throws RemoteException {

    }

    private static FOFGenerateLogic instance;

    public static FOFGenerateLogic getInstance() {
        if (instance == null)
            try {
                instance = new FOFGenerateLogicImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        return instance;
    }

    @Override
    public Map<String, Double> getLargeClassConfiguration() {
        return null;
    }

    @Override
    public Map<String, Map<String, Double>> getSmallClassConfiguration() {
        return null;
    }
}
