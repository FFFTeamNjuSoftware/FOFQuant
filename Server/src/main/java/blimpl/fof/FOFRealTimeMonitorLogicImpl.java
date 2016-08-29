package blimpl.fof;

import beans.FundInFOFQuickInfo;
import bl.fof.FOFProfitStatisticsLogic;
import bl.fof.FOFRealTimeMonitorLogic;
import exception.ObjectNotFoundException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by Daniel on 2016/8/26.
 */
public class FOFRealTimeMonitorLogicImpl extends UnicastRemoteObject implements FOFRealTimeMonitorLogic {
    private FOFRealTimeMonitorLogicImpl() throws RemoteException {

    }

    private static FOFRealTimeMonitorLogic instance;

    public static FOFRealTimeMonitorLogic getInstance() {
        if (instance == null)
            try {
                instance = new FOFRealTimeMonitorLogicImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        return instance;
    }
    @Override
    public List<FundInFOFQuickInfo> getFundInFOFQuickinfo() throws RemoteException {
        return null;
    }

    @Override
    public void setProformanceBase(String indexCode) throws RemoteException, ObjectNotFoundException {

    }
}
