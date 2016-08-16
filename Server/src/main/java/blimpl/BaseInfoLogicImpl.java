package blimpl;

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
public class BaseInfoLogicImpl extends UnicastRemoteObject implements BaseInfoLogic {

    private static BaseInfoLogic instance;

    private BaseInfoLogicImpl() throws RemoteException {
    }

    public static BaseInfoLogic getInstance() {
        if (instance == null)
            try {
                instance = new BaseInfoLogicImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        return instance;
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
