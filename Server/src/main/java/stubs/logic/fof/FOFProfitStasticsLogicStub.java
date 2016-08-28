package stubs.logic.fof;

import beans.PriceInfo;
import beans.ProfitStatisticsInfo;
import bl.fof.FOFProfitStatisticsLogic;
import exception.ObjectNotFoundException;
import exception.ParameterException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by Daniel on 2016/8/28.
 */
public class FOFProfitStasticsLogicStub extends UnicastRemoteObject implements
        FOFProfitStatisticsLogic {
    public FOFProfitStasticsLogicStub()throws RemoteException{

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
    public void setUnitType() throws RemoteException {

    }

    @Override
    public ProfitStatisticsInfo getProfitStatisticsInfo() throws RemoteException {
        return null;
    }

    @Override
    public List<PriceInfo> getPriceInfo() throws RemoteException {
        return null;
    }
}
