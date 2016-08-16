package stubs.dataservice;

import dataservice.InvestmentPortfolioDataService;
import entities.AssetAllocation;
import entities.BondHoldInfoEntity;
import entities.IndustryHoldInfoEntity;
import entities.StockHoldInfoEntity;
import exception.ObjectNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 2016/8/16.
 */
public class InvestmentPortfolioDataServiceStub implements InvestmentPortfolioDataService {
	@Override
	public List<BondHoldInfoEntity> getBondHoldInfo(String code) throws ObjectNotFoundException {
		List<BondHoldInfoEntity> bondHoldInfoEntityList = new ArrayList<BondHoldInfoEntity>();
		BondHoldInfoEntity bondHoldInfoEntity=new BondHoldInfoEntity();
		bondHoldInfoEntity.setBondCode("010107");
		bondHoldInfoEntity.setBondName("21国债（7）");
		bondHoldInfoEntity.setDate("20160101");
		bondHoldInfoEntity.setFundCode("001547");
		bondHoldInfoEntity.setHoldNum(1000.0);
		bondHoldInfoEntity.setRatio(0.5);
		bondHoldInfoEntity.setValue(2000.0);
		bondHoldInfoEntityList.add(bondHoldInfoEntity);
		return bondHoldInfoEntityList;
	}

	@Override
	public List<StockHoldInfoEntity> getStockHoldInfo(String code) throws ObjectNotFoundException {
		List<StockHoldInfoEntity> stockHoldInfoEntityList = new ArrayList<StockHoldInfoEntity>();
		StockHoldInfoEntity stockHoldInfoEntity=new StockHoldInfoEntity();
		stockHoldInfoEntity.setStockCode("600000");
		stockHoldInfoEntity.setStockName("浦发银行");
		stockHoldInfoEntity.setDate("20160101");
		stockHoldInfoEntity.setFundCode("001547");
		stockHoldInfoEntity.setHoldNum(1000.0);
		stockHoldInfoEntity.setRatio(0.5);
		stockHoldInfoEntity.setValue(2000.0);
		stockHoldInfoEntityList.add(stockHoldInfoEntity);
		return stockHoldInfoEntityList;
	}

	@Override
	public List<IndustryHoldInfoEntity> getIndustryHold(String code) throws ObjectNotFoundException {
		List<IndustryHoldInfoEntity> industryHoldInfoEntityList = new ArrayList<IndustryHoldInfoEntity>();
		IndustryHoldInfoEntity industryHoldInfoEntity=new IndustryHoldInfoEntity();
		industryHoldInfoEntity.setIndustryCode("");
		industryHoldInfoEntity.setIndustryName("");
		industryHoldInfoEntity.setFundCode("001547");
		industryHoldInfoEntity.setDate("20160101");
		industryHoldInfoEntity.setRatio(0.5);
		industryHoldInfoEntity.setValue(1000.0);
		industryHoldInfoEntityList.add(industryHoldInfoEntity);
		return industryHoldInfoEntityList;
	}

	@Override
	public List<AssetAllocation> getAssetAllocations(String code) throws ObjectNotFoundException {
		List<AssetAllocation> assetAllocationEntityList = new ArrayList<AssetAllocation>();
		AssetAllocation assetAllocationEntity=new AssetAllocation();
		assetAllocationEntity.setBondRatio(1.0);
		assetAllocationEntity.setBondValue(1000.0);
		assetAllocationEntity.setCode("010107");
		assetAllocationEntity.setCashRatio(0.2);
		assetAllocationEntity.setCashValue(2000.0);
		assetAllocationEntity.setDate(new java.sql.Date(2016-1900,1-1,1));
		assetAllocationEntity.setNetValue(1000.0);
		assetAllocationEntity.setOtherRatio(0.30);
		assetAllocationEntity.setStockRatio(0.4);
		assetAllocationEntity.setStockRatio(4000.0);
		assetAllocationEntityList.add(assetAllocationEntity);
		return assetAllocationEntityList;
	}
}
