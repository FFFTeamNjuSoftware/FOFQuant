package stubs.logic;

import beans.CodeName;
import beans.FundInfo;
import bl.BaseInfoLogic;
import exception.ObjectNotFoundException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by Daniel on 2016/8/15.
 */
public class BaseInfoLogicStub  extends UnicastRemoteObject implements BaseInfoLogic{
    public BaseInfoLogicStub() throws RemoteException{

    }

    @Override
    public List<String> getFundCodes() throws RemoteException {
        return null;
    }

    @Override
    public List<CodeName> fuzzySearch(String keyword) throws RemoteException {
        return null;
    }

    @Override
    public FundInfo getFundBaseInfo(String code) throws RemoteException, ObjectNotFoundException {
        return null;
    }
}
