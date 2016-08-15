package stubs;

import beans.CodeName;
import beans.FundInfo;
import bl.BaseInfoLogic;
import exception.ObjectNotFoundException;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Daniel on 2016/8/15.
 */
public class BaseInfoLogicStub  implements BaseInfoLogic{
    @Override
    public List<String> getFundCodes() throws RemoteException {
        return null;
    }

    @Override
    public List<CodeName> fuzzySearch(String keyword) throws RemoteException {
        return null;
    }

    @Override
    public FundInfo getFundBaseInfo() throws RemoteException, ObjectNotFoundException {
        return null;
    }
}
