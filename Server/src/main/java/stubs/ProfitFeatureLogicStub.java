package stubs;

import bl.ProfitFeatureLogic;

import java.rmi.RemoteException;

/**
 * Created by Daniel on 2016/8/15.
 */
public class ProfitFeatureLogicStub implements ProfitFeatureLogic {
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
