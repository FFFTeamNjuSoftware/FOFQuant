package blimpl.fof;

import beans.FOFProfitAnalyse;
import bl.fof.FOFPerformanceAttributionLogic;
import bl.fof.FOFProfitAnalyseLogic;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import util.TimeType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Daniel on 2016/8/26.
 */
public class FOFProfitAnalyseLogicImpl extends UnicastRemoteObject implements FOFProfitAnalyseLogic {
    private  FOFProfitAnalyseLogicImpl() throws RemoteException {

    }

    private static FOFProfitAnalyseLogic instance;

    public static  FOFProfitAnalyseLogic getInstance() {
        if (instance == null)
            try {
                instance = new  FOFProfitAnalyseLogicImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        return instance;
    }
    @Override
    public void setStartDate(String startDate) throws ParameterException, RemoteException {

    }

    @Override
    public void setEndDate(String endDate) throws ParameterException, RemoteException {

    }

    @Override
    public void setProformanceBase(String indexCode) throws RemoteException, ObjectNotFoundException {

    }

    @Override
    public FOFProfitAnalyse getFOFProfitAnalyse(TimeType timeType) throws RemoteException {
        return null;
    }
}
