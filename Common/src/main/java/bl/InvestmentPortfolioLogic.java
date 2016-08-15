package bl;

/**
 * Created by Daniel on 2016/8/15.
 */

import beans.AssetAllocation;
import beans.HoldingInfo;
import exception.ObjectNotFoundException;
import util.HoldingType;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * 投资组合信息
 */
public interface InvestmentPortfolioLogic extends Remote {
    /**
     * 获得指定基金代码的大类资产配置信息
     *
     * @param code
     * @return
     * @throws RemoteException
     * @throws ObjectNotFoundException
     */
    public List<AssetAllocation> getAssetAllocation(String code) throws RemoteException,
            ObjectNotFoundException;

    /**
     * 获得指定基金代码的指定类型的配置信息
     *
     * @param code 基金代码
     * @param type 有股票、债券、行业三种
     * @return
     * @throws RemoteException
     * @throws ObjectNotFoundException
     */
    public List<HoldingInfo> getHoldingInfos(String code, HoldingType type) throws
            RemoteException, ObjectNotFoundException;
}
