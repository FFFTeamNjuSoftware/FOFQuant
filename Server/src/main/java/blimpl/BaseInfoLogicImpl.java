package blimpl;

import beans.CodeName;
import beans.FundInfo;
import beans.FundQuickInfo;
import bl.BaseInfoLogic;
import dataservice.BaseInfoDataService;
import dataserviceimpl.DataServiceController;
import exception.ObjectNotFoundException;
import util.NumberOpe;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 2016/8/15.
 */
public class BaseInfoLogicImpl extends UnicastRemoteObject implements BaseInfoLogic {

    private static BaseInfoLogic instance;

    private BaseInfoDataService baseInfoDataService;

    private BaseInfoLogicImpl() throws RemoteException {
        baseInfoDataService = DataServiceController.getBaseInfoDataService();
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
        return baseInfoDataService.getAllCodes();
    }

    @Override
    public List<CodeName> fuzzySearch(String keyword) throws RemoteException {
        return baseInfoDataService.fuzzySearch(keyword);
    }

    @Override
    public FundInfo getFundBaseInfo(String code) throws RemoteException, ObjectNotFoundException {
        return Converter.convertFundInfo(baseInfoDataService.getFundInfo(code));
    }

    @Override
    public List<FundQuickInfo> getFundQuickInfo(String sectorId) throws RemoteException, ObjectNotFoundException {
        List<String> codes = baseInfoDataService.getSectorCodes(sectorId);
        List<FundQuickInfo> infos = new ArrayList<>();
        for (String code : codes) {
            try {
                FundQuickInfo quickInfo = Converter.convertFundQuickInfo(baseInfoDataService
                        .getFundQuickInfo(code));
                NumberOpe.controlDecimal(quickInfo, 2);
                infos.add(quickInfo);
            } catch (ObjectNotFoundException e) {
                e.printStackTrace();
                continue;
            }
        }
        return infos;
    }
}
