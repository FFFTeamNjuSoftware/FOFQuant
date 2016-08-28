package blimpl.fof;

import beans.PerformanceAttribution;
import bl.fof.FOFPerformanceAttributionLogic;
import exception.ObjectNotFoundException;
import exception.ParameterException;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Daniel on 2016/8/26.
 */
public class FOFPerformanceAttributionLogicImpl implements FOFPerformanceAttributionLogic {
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
    public List<PerformanceAttribution> getPerformanceAttribution() throws RemoteException {
        return null;
    }
}
