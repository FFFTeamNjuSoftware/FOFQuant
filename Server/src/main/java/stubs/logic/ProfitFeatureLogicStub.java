package stubs.logic;

import bl.ProfitFeatureLogic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Daniel on 2016/8/15.
 */
public class ProfitFeatureLogicStub extends UnicastRemoteObject implements ProfitFeatureLogic {
    public ProfitFeatureLogicStub() throws RemoteException{

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
