package blimpl;

import beans.CodeName;
import beans.ConstParameter;
import beans.FundInfo;
import beans.FundQuickInfo;
import bl.BaseInfoLogic;
import dataservice.BaseInfoDataService;
import dataserviceimpl.DataServiceController;
import exception.ObjectNotFoundException;
import util.NumberOpe;
import util.SectorType;

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
    public List<ArrayList<String>> getRankSectorType() throws RemoteException {
        List<ArrayList<String>> sectorTypes=new ArrayList<>();
        ArrayList<String> fixprofit=new ArrayList<>();
        fixprofit.add(SectorType.BOND_TYPE);
        sectorTypes.add(fixprofit);

        ArrayList<String> right=new ArrayList<>();
        right.add(SectorType.STOCK_TYPE);
        right.add(SectorType.MIX_TYPE);
        sectorTypes.add(right);

        ArrayList<String> other=new ArrayList<>();
        other.add(SectorType.INDEX_TYPE);
        other.add(SectorType.ETF_TYPE);
        other.add(SectorType.LOF_TYPE);
        other.add(SectorType.OPEN_TYPE);
        other.add(SectorType.QDII_TYPE);
        sectorTypes.add(other);

        return sectorTypes;
    }

    @Override
    public List<FundQuickInfo> getFundQuickInfo(String sectorId) throws RemoteException, ObjectNotFoundException {
        List<String> codes = null;
        for (String str : SectorType.COMPONENT_INFO.keySet()) {
            if (sectorId.equals(str)) {
                codes = new ArrayList<>();
                for (String componentId : SectorType.COMPONENT_INFO.get(str)) {
                    codes.addAll(baseInfoDataService.getSectorCodes(componentId));
                }
            }
        }
        if (codes == null)
            codes = baseInfoDataService.getSectorCodes(sectorId);
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

    @Override
    public ConstParameter getConstaParameteer() throws RemoteException {
        return Converter.convertConstParameter(baseInfoDataService.getConstParameter());
    }
}
