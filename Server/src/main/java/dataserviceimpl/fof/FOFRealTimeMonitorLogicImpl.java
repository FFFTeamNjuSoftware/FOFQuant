package dataserviceimpl.fof;

import beans.FundInFOFQuickInfo;
import bl.fof.FOFRealTimeMonitorLogic;
import exception.ObjectNotFoundException;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Daniel on 2016/8/26.
 */
public class FOFRealTimeMonitorLogicImpl implements FOFRealTimeMonitorLogic {
    @Override
    public List<FundInFOFQuickInfo> getFundInFOFQuickinfo() throws RemoteException {
        return null;
    }

    @Override
    public void setProformanceBase(String indexCode) throws RemoteException, ObjectNotFoundException {

    }
}
