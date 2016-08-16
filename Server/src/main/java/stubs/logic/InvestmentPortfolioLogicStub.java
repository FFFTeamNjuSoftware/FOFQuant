package stubs.logic;

import beans.AssetAllocation;
import beans.HoldingInfo;
import bl.InvestmentPortfolioLogic;
import exception.ObjectNotFoundException;
import util.HoldingType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by Daniel on 2016/8/15.
 */
public class InvestmentPortfolioLogicStub extends UnicastRemoteObject implements
        InvestmentPortfolioLogic {
    public InvestmentPortfolioLogicStub() throws RemoteException{

    }
    @Override
    public List<AssetAllocation> getAssetAllocation(String code) throws RemoteException, ObjectNotFoundException {
        return null;
    }

    @Override
    public List<HoldingInfo> getHoldingInfos(String code, HoldingType type) throws RemoteException, ObjectNotFoundException {
        return null;
    }
}
