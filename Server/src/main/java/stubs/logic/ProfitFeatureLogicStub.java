package stubs.logic;

import beans.InvestStyleAnalyse;
import beans.RiskProfitIndex;
import bl.ProfitFeatureLogic;
import exception.ObjectNotFoundException;

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
        return 1;
    }

    @Override
    public double aveProfitRate(String code) throws RemoteException {
        return 2;
    }

    @Override
    public double riskProfitRate(String code) throws RemoteException {
        return 3;
    }

    @Override
    public double getEnsembleAveProfitRate(String code) throws RemoteException {
        return 4;
    }

    @Override
    public RiskProfitIndex getRiskProfitIndex(String code) throws RemoteException, ObjectNotFoundException {
        return null;
    }

    @Override
    public InvestStyleAnalyse getInvestStyleAnalyse(String code) throws RemoteException, ObjectNotFoundException {
        return null;
    }
}
