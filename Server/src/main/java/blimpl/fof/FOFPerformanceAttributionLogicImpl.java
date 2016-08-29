package blimpl.fof;

import beans.PerformanceAttribution;
import bl.fof.FOFGenerateLogic;
import bl.fof.FOFPerformanceAttributionLogic;
import exception.ObjectNotFoundException;
import exception.ParameterException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by Daniel on 2016/8/26.
 */
public class FOFPerformanceAttributionLogicImpl extends UnicastRemoteObject implements
        FOFPerformanceAttributionLogic {

    private FOFPerformanceAttributionLogicImpl() throws RemoteException {

    }

    private static FOFPerformanceAttributionLogic instance;

    public static FOFPerformanceAttributionLogic getInstance() {
        if (instance == null)
            try {
                instance = new FOFPerformanceAttributionLogicImpl();
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
    public List<PerformanceAttribution> getPerformanceAttribution() throws RemoteException {
        return null;
    }
}
