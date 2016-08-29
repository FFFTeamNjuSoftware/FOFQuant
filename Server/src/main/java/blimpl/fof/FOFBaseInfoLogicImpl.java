package blimpl.fof;

import beans.FOFQuickInfo;
import beans.PriceInfo;
import bl.fof.FOFAssetAllocationLogic;
import bl.fof.FOFBaseInfoLogic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by Daniel on 2016/8/26.
 */
public class FOFBaseInfoLogicImpl extends UnicastRemoteObject implements FOFBaseInfoLogic{
    private FOFBaseInfoLogicImpl() throws RemoteException {

    }

    private static FOFBaseInfoLogic instance;

    public static FOFBaseInfoLogic getInstance() {
        if (instance == null)
            try {
                instance = new FOFBaseInfoLogicImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        return instance;
    }
    @Override
    public FOFQuickInfo getFOFQuickInfo() throws RemoteException {
        return null;
    }

    @Override
    public FOFQuickInfo getFOFQuickInfo(String code) throws RemoteException {
        return null;
    }

    @Override
    public List<PriceInfo> getFOFPriceInfos() throws RemoteException {
        return null;
    }
}
