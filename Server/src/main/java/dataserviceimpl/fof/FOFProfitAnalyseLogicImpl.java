package dataserviceimpl.fof;

import beans.FOFProfitAnalyse;
import bl.fof.FOFProfitAnalyseLogic;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import util.TimeType;

import java.rmi.RemoteException;

/**
 * Created by Daniel on 2016/8/26.
 */
public class FOFProfitAnalyseLogicImpl implements FOFProfitAnalyseLogic {
    @Override
    public void setStartDate() throws ParameterException, RemoteException {

    }

    @Override
    public void setEndDate() throws ParameterException, RemoteException {

    }

    @Override
    public void setProformanceBase(String indexCode) throws RemoteException, ObjectNotFoundException {

    }

    @Override
    public FOFProfitAnalyse getFOFProfitAnalyse(TimeType timeType) throws RemoteException {
        return null;
    }
}
