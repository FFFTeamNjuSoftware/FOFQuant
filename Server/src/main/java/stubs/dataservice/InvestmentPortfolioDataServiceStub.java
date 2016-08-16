package stubs.dataservice;

import dataservice.InvestmentPortfolioDataService;
import entities.AssetAllocationEntity;
import entities.BondHoldInfoEntity;
import entities.IndustryHoldInfoEntity;
import entities.StockHoldInfoEntity;
import exception.ObjectNotFoundException;

import java.util.List;

/**
 * Created by Daniel on 2016/8/16.
 */
public class InvestmentPortfolioDataServiceStub implements InvestmentPortfolioDataService {
    @Override
    public List<BondHoldInfoEntity> getBondHoldInfo(String code) throws ObjectNotFoundException {
        return null;
    }

    @Override
    public List<StockHoldInfoEntity> getStockHoldInfo(String code) throws ObjectNotFoundException {
        return null;
    }

    @Override
    public List<IndustryHoldInfoEntity> getIndustryHold(String code) throws ObjectNotFoundException {
        return null;
    }

    @Override
    public List<AssetAllocationEntity> getAssetAllocations(String code) throws ObjectNotFoundException {
        return null;
    }
}
