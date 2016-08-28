package stubs.logic.fof;

import beans.FundInFOFQuickInfo;
import bl.fof.FOFRealTimeMonitorLogic;
import exception.ObjectNotFoundException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by Daniel on 2016/8/28.
 */
public class FOFRealTimeMonitorLogicStub extends UnicastRemoteObject implements FOFRealTimeMonitorLogic {
    public FOFRealTimeMonitorLogicStub() throws RemoteException {
    }

    @Override
    public List<FundInFOFQuickInfo> getFundInFOFQuickinfo() throws RemoteException {
        return null;
    }

    @Override
    public void setProformanceBase(String indexCode) throws RemoteException, ObjectNotFoundException {

    }
}
