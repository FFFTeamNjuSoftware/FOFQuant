package blimpl;

import beans.AssetAllocation;
import beans.HoldingInfo;
import bl.InvestmentPortfolioLogic;
import dataservice.InvestmentPortfolioDataService;
import dataserviceimpl.DataServiceController;
import entities.BondHoldInfoEntity;
import entities.IndustryHoldInfoEntity;
import entities.StockHoldInfoEntity;
import exception.ObjectNotFoundException;
import util.HoldingType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Daniel on 2016/8/15.
 */
public class InvestmentPortfolioLogicImpl extends UnicastRemoteObject implements InvestmentPortfolioLogic {
    private static InvestmentPortfolioLogic instance;
    private InvestmentPortfolioDataService investmentPortfolioDataService;

    private InvestmentPortfolioLogicImpl() throws RemoteException {
        investmentPortfolioDataService = DataServiceController.getInvestmentPortfolioDataService();
    }

    public static InvestmentPortfolioLogic getInstance() {
        if (instance == null)
            try {
                instance = new InvestmentPortfolioLogicImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        return instance;
    }


    @Override
    public List<AssetAllocation> getAssetAllocation(String code) throws RemoteException, ObjectNotFoundException {
        return investmentPortfolioDataService.getAssetAllocations(code).stream().map
                (Converter::convertAssetAllocation).collect(Collectors.toList());
    }

    @Override
    public List<HoldingInfo> getHoldingInfos(String code, HoldingType type) throws RemoteException, ObjectNotFoundException {
        List<HoldingInfo> infos = new ArrayList<>();
        switch (type) {
            case STOCK:
                List<StockHoldInfoEntity> stockEntities = investmentPortfolioDataService
                        .getStockHoldInfo(code);
                Map<String, List<StockHoldInfoEntity>> stockEntityGroupByDate = stockEntities.stream().collect
                        (Collectors.groupingBy(e -> e.getDate()));
                for (String key : stockEntityGroupByDate.keySet()) {
                    HoldingInfo info = new HoldingInfo();
                    info.date = key;
                    info.items = stockEntityGroupByDate.get(key).stream().map
                            (Converter::convertHoldingUnit).collect(Collectors.toList());
                    infos.add(info);
                }
                break;
            case BOND:
                List<BondHoldInfoEntity> bondEntities = investmentPortfolioDataService
                        .getBondHoldInfo(code);
                Map<String, List<BondHoldInfoEntity>> bondEntityGroupByDate = bondEntities.stream().collect
                        (Collectors.groupingBy(e -> e.getDate()));
                for (String key : bondEntityGroupByDate.keySet()) {
                    HoldingInfo info = new HoldingInfo();
                    info.date = key;
                    info.items = bondEntityGroupByDate.get(key).stream().map
                            (Converter::convertHoldingUnit).collect(Collectors.toList());
                    infos.add(info);
                }
                break;
            case INDUSTRY:
                List<IndustryHoldInfoEntity> industryEntities = investmentPortfolioDataService
                        .getIndustryHold(code);
                Map<String, List<IndustryHoldInfoEntity>> industryEntityGroupByDate = industryEntities.stream().collect
                        (Collectors.groupingBy(e -> e.getDate()));
                for (String key : industryEntityGroupByDate.keySet()) {
                    HoldingInfo info = new HoldingInfo();
                    info.date = key;
                    info.items = industryEntityGroupByDate.get(key).stream().map
                            (Converter::convertHoldingUnit).collect(Collectors.toList());
                    infos.add(info);
                }
                break;
        }
        Collections.sort(infos, (e1, e2) -> e2.date.compareTo(e1.date));
        return infos;
    }
}
