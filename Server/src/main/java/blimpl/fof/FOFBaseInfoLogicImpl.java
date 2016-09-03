package blimpl.fof;

import beans.FOFHistoryInfo;
import beans.FOFQuickInfo;
import beans.PriceInfo;
import bl.fof.FOFBaseInfoLogic;
import blimpl.BLController;
import blimpl.Converter;
import dataservice.FOFDataService;
import dataserviceimpl.DataServiceController;
import exception.ObjectNotFoundException;
import util.FOFUtilInfo;
import util.NumberOpe;
import util.UnitType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

/**
 * Created by Daniel on 2016/8/26.
 */
public class FOFBaseInfoLogicImpl extends UnicastRemoteObject implements FOFBaseInfoLogic {
    private FOFDataService fofDataService;
    private String fof_code;

    private FOFBaseInfoLogicImpl() throws RemoteException {
        fofDataService = DataServiceController.getFOFDataService();
        fof_code = FOFUtilInfo.FOF_CODE;
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
    public List<FOFHistoryInfo> getFOFHistoryInfo() throws RemoteException {
        return null;
    }

    @Override
    public Map<String, List<String>> getFundCodeInFOF() throws RemoteException {
        return null;
    }

    @Override
    public FOFQuickInfo getFOFQuickInfo() throws RemoteException {
        return getFOFQuickInfo(fof_code);
    }

    @Override
    public FOFQuickInfo getFOFQuickInfo(String code) throws RemoteException {
        try {
            FOFQuickInfo quickInfo = Converter.convertFOFQuickinfo(fofDataService.getFofInfoEntity
                    (code));
            NumberOpe.controlDecimal(quickInfo, 2);
            return quickInfo;
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PriceInfo> getFOFPriceInfos() throws RemoteException {
        try {
            return BLController.getMarketLogic().getPriceInfo(fof_code, UnitType.DAY);
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
