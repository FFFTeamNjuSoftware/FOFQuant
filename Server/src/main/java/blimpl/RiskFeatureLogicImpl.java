package blimpl;

import bl.RiskFeatureLogic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Daniel on 2016/8/15.
 */
public class RiskFeatureLogicImpl extends UnicastRemoteObject implements RiskFeatureLogic {
    private static RiskFeatureLogic instance;

    private RiskFeatureLogicImpl() throws RemoteException {
    }

    public static RiskFeatureLogic getInstance() {
        if (instance == null)
            try {
                instance = new RiskFeatureLogicImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        return instance;
    }

    @Override
    public double getStandardDeviation(String code) throws RemoteException {
        return 0;
    }

    @Override
    public double getBeta(String code) throws RemoteException {
        AlphaBetaCal alphaBetaCal = new AlphaBetaCal(code);
        double[] re = alphaBetaCal.getAlphaBeta();
        if (re == null) {
            return 0;
        }
        return re[1];
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
