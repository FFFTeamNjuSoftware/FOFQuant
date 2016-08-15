package blimpl;

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
public class InvestmentPortfolioLogicImpl extends UnicastRemoteObject implements InvestmentPortfolioLogic {
    private static InvestmentPortfolioLogic instance;

    private InvestmentPortfolioLogicImpl() throws RemoteException{
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
        return null;
    }

    @Override
    public List<HoldingInfo> getHoldingInfos(String code, HoldingType type) throws RemoteException, ObjectNotFoundException {
        return null;
    }
}
