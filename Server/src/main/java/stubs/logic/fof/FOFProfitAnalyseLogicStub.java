package stubs.logic.fof;

import beans.FOFProfitAnalyse;
import bl.fof.FOFProfitAnalyseLogic;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import util.TimeType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Daniel on 2016/8/28.
 */

public class FOFProfitAnalyseLogicStub extends UnicastRemoteObject implements FOFProfitAnalyseLogic {
    public FOFProfitAnalyseLogicStub() throws RemoteException{

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
