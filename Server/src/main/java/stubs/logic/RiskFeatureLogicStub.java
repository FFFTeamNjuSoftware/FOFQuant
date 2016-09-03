package stubs.logic;

import bl.RiskFeatureLogic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Daniel on 2016/8/15.
 */
public class RiskFeatureLogicStub  extends UnicastRemoteObject implements RiskFeatureLogic {
    public RiskFeatureLogicStub() throws RemoteException{

    }
    @Override
    public double getStandardDeviation(String code) throws RemoteException {
        return 1;
    }

    @Override
    public double yearWaveRate(String code) throws RemoteException {
        return 0;
    }

    @Override
    public double getBeta(String code) throws RemoteException {
        return 2;
    }

    @Override
    public double getJansen(String code) throws RemoteException {
        return 3;
    }

    @Override
    public double getTreynor(String code) throws RemoteException {
        return 4;
    }

    @Override
    public double getSharpe(String code) throws RemoteException {
        return 5;
    }

}
