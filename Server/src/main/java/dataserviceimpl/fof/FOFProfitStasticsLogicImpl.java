package dataserviceimpl.fof;

import beans.PriceInfo;
import beans.ProfitStatisticsInfo;
import bl.fof.FOFProfitStatisticsLogic;
import exception.ObjectNotFoundException;
import exception.ParameterException;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Daniel on 2016/8/26.
 */
public class FOFProfitStasticsLogicImpl implements FOFProfitStatisticsLogic {
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
