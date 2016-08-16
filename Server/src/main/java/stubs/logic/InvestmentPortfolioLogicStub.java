package stubs.logic;

import beans.HoldingInfo;
import beans.HoldingUnit;
import bl.InvestmentPortfolioLogic;
import entities.AssetAllocationEntity;
import exception.ObjectNotFoundException;
import util.HoldingType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 2016/8/15.
 */
public class InvestmentPortfolioLogicStub extends UnicastRemoteObject implements
        InvestmentPortfolioLogic {
    public InvestmentPortfolioLogicStub() throws RemoteException{

    }
    @Override
    public List<beans.AssetAllocation> getAssetAllocation(String code) throws RemoteException, ObjectNotFoundException {
        List<beans.AssetAllocation>  assetAllocationList= new ArrayList<beans.AssetAllocation>();
        AssetAllocationEntity assetAllocation=new AssetAllocationEntity();
        assetAllocation.setBondRatio(1.0);
        assetAllocation.setBondValue(1000.0);
        assetAllocation.setCode("010107");
        assetAllocation.setCashRatio(0.2);
        assetAllocation.setCashValue(2000.0);
        assetAllocation.setDate("2016-06-01");
        assetAllocation.setNetValue(1000.0);
        assetAllocation.setOtherRatio(0.30);
        assetAllocation.setStockRatio(0.4);
        assetAllocation.setStockRatio(4000.0);
        return assetAllocationList;
    }

    @Override
    public List<HoldingInfo> getHoldingInfos(String code, HoldingType type) throws RemoteException, ObjectNotFoundException {
        List<HoldingInfo>  holdingInfoList= new ArrayList<HoldingInfo>();

        List<HoldingUnit> holdingUnitList= new ArrayList<HoldingUnit>();
        HoldingUnit holdingUnit=new HoldingUnit();
        holdingUnit.code="001547";
        holdingUnit.holdNum=1000.0;
        holdingUnit.name="组合一";
        holdingUnit.ratio=0.3;
        holdingUnit.value=3000.0;
        holdingUnitList.add(holdingUnit);

        HoldingInfo holdingInfo=new HoldingInfo();
        holdingInfo.date="20160101";
        holdingInfo.items=holdingUnitList;

        return holdingInfoList;
    }
}
