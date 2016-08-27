package bl.fof;

import beans.FOFQuickInfo;
import beans.PriceInfo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Daniel on 2016/8/26.
 */
public interface FOFBaseInfoLogic extends Remote {
    /**
     * 返回FOF组合快速信息
     *
     * @return
     * @throws RemoteException
     */
    FOFQuickInfo getFOFQuickInfo() throws RemoteException;

    FOFQuickInfo getFOFQuickInfo(String code) throws RemoteException;

    List<PriceInfo> getFOFPriceInfos() throws RemoteException;
}
