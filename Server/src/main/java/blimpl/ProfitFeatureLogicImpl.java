package blimpl;

import bl.ProfitFeatureLogic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Daniel on 2016/8/15.
 */
public class ProfitFeatureLogicImpl extends UnicastRemoteObject implements ProfitFeatureLogic {

    private static ProfitFeatureLogic instance;

    private ProfitFeatureLogicImpl() throws RemoteException {
    }

    public static ProfitFeatureLogic getInstance() {
        if (instance == null)
            try {
                instance = new ProfitFeatureLogicImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        return instance;
    }

    @Override
    public double getAlpha(String code) throws RemoteException {
        return 0;
    }

    @Override
    public double aveProfitRate(String code) throws RemoteException {
        return 0;
    }

    @Override
    public double riskProfitRate(String code) throws RemoteException {
        return 0;
    }

    @Override
    public double getEnsembleAveProfitRate(String code) throws RemoteException {
        return 0;
    }
}
