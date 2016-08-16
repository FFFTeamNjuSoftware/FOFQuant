package stubs.logic;

import beans.PriceInfo;
import bl.MarketLogic;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import util.UnitType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by Daniel on 2016/8/15.
 */
public class MarketLogicStub extends UnicastRemoteObject implements MarketLogic {
    public MarketLogicStub() throws RemoteException{

    }
    @Override
    public List<PriceInfo> getPriceInfo(String code, UnitType type) throws RemoteException, ObjectNotFoundException, ParameterException {
        return null;
    }

    @Override
    public List<PriceInfo> getPriceInfo(String code, UnitType type, int counts) throws RemoteException, ObjectNotFoundException, ParameterException {
        return null;
    }

    @Override
    public List<PriceInfo> getPriceInfo(String code, UnitType type, String startDate, String endDate) throws RemoteException, ObjectNotFoundException, ParameterException {
        return null;
    }
}
