package blimpl.fof;

import beans.PriceInfo;
import beans.ProfitStatisticsInfo;
import bl.fof.FOFProfitStatisticsLogic;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import util.UnitType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by Daniel on 2016/8/26.
 */
public class FOFProfitStatisticsLogicImpl extends UnicastRemoteObject implements
        FOFProfitStatisticsLogic {
    private FOFProfitStatisticsLogicImpl() throws RemoteException {

    }

    private static FOFProfitStatisticsLogic instance;

    public static FOFProfitStatisticsLogic getInstance() {
        if (instance == null)
            try {
                instance = new FOFProfitStatisticsLogicImpl();
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
    public void setUnitType(UnitType unitType) throws RemoteException {

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
