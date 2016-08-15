package stubs;

import bl.RiskFeatureLogic;

import java.rmi.RemoteException;

/**
 * Created by Daniel on 2016/8/15.
 */
public class RiskFeatureLogicStub implements RiskFeatureLogic {
    @Override
    public double getStandardDeviation(String code) throws RemoteException {
        return 0;
    }

    @Override
    public double getBeta(String code) throws RemoteException {
        return 0;
    }

    @Override
    public double getJansen(String code) throws RemoteException {
        return 0;
    }

    @Override
    public double getTreynor(String code) throws RemoteException {
        return 0;
    }

    @Override
    public double getSharpe(String code) throws RemoteException {
        return 0;
    }
}
