package blimpl;

import beans.*;
import bl.BaseInfoLogic;
import dataservice.BaseInfoDataService;
import dataserviceimpl.DataServiceController;
import entities.FundInfosEntity;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import util.UnitType;

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
            FundQuickInfo quickInfo = new FundQuickInfo();
            FundInfosEntity entity = baseInfoDataService.getFundInfo(code);
            quickInfo.code = code;
            quickInfo.simple_name = entity.getSimpleName();
            PriceInfo priceInfo = null;
            try {
                priceInfo = BLController.getMarketLogic().getPriceInfo(code, UnitType
                        .DAY, 1).get(0);
            } catch (ParameterException e) {
                e.printStackTrace();
            }
            quickInfo.current_netWorth = priceInfo.price;
            quickInfo.daily_rise = priceInfo.rise;
            ProfitRateInfo profitRateInfo = BLController.getMarketLogic().getProfitRateInfo(code);
            quickInfo.oneMonth = profitRateInfo.nearOneMonth;
            quickInfo.threeMonth = profitRateInfo.nearThreeMonth;
            quickInfo.halfYear = profitRateInfo.nearSixMonth;
            quickInfo.oneYear = profitRateInfo.nearOneYear;
            quickInfo.threeYear = profitRateInfo.nearThreeYear;
            quickInfo.fiveYear = profitRateInfo.nearFiveYear;
            infos.add(quickInfo);
        }
        return infos;
    }
}
