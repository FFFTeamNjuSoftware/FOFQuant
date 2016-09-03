package blimpl.fof;

import beans.FOFHistoryInfo;
import beans.FOFQuickInfo;
import beans.PriceInfo;
import bl.BaseInfoLogic;
import bl.fof.FOFBaseInfoLogic;
import blimpl.BLController;
import blimpl.Converter;
import dataservice.BaseInfoDataService;
import dataservice.FOFDataService;
import dataserviceimpl.DataServiceController;
import entities.FofEstablishInfoEntity;
import exception.ObjectNotFoundException;
import util.FOFUtilInfo;
import util.NumberOpe;
import util.SectorType;
import util.UnitType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Daniel on 2016/8/26.
 */
public class FOFBaseInfoLogicImpl extends UnicastRemoteObject implements FOFBaseInfoLogic {
    private FOFDataService fofDataService;
    private BaseInfoLogic baseInfoLogic;
    private String fof_code;

    private FOFBaseInfoLogicImpl() throws RemoteException {
        baseInfoLogic=BLController.getBaseInfoLogic();
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
        try {
            return fofDataService.getFofHistoryInfos(fof_code).stream().map
                    (Converter::convertFOFHistoryInfo).collect(Collectors.toList());
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, List<String>> getFundCodeInFOF() throws RemoteException {
        try {
            List<String> fixProfitFundCodes = baseInfoLogic.getSectorCodes(SectorType
                    .FIX_PROFIT_TYPE);
            List<String> rightsFundCodes = baseInfoLogic.getSectorCodes(SectorType.RIGHTS_TYPE);
            List<String> allCodes = fofDataService.getFofEstablishInfo(fof_code).stream().map
                    (FofEstablishInfoEntity::getFundCode).collect(Collectors.toList());
            Map<String, List<String>> result = new HashMap<>();
            result.put(SectorType.FIX_PROFIT_TYPE, new ArrayList<>());
            result.put(SectorType.RIGHTS_TYPE, new ArrayList<>());
            for (String code : allCodes) {
                if (fixProfitFundCodes.contains(code))
                    result.get(SectorType.FIX_PROFIT_TYPE).add(code);
                if (rightsFundCodes.contains(code))
                    result.get(SectorType.RIGHTS_TYPE).add(code);
            }
            return result;
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
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
