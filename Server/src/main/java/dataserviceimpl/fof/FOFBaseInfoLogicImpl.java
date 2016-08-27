package dataserviceimpl.fof;

import beans.FOFQuickInfo;
import beans.PriceInfo;
import bl.fof.FOFBaseInfoLogic;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Daniel on 2016/8/26.
 */
public class FOFBaseInfoLogicImpl implements FOFBaseInfoLogic{
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
