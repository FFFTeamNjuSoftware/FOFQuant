package bl.fof;

import beans.FundInFOFQuickInfo;
import exception.ObjectNotFoundException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Daniel on 2016/8/26.
 */

/**
 * 实时监控
 */
public interface FOFRealTimeMonitorLogic extends Remote {
    /**
     * 获得实时监控的基金信息
     * @return
     * @throws RemoteException
     */
    List<FundInFOFQuickInfo> getFundInFOFQuickinfo() throws RemoteException;

    /**
     * 设置业绩基准，大盘指数等
     * @param indexCode
     * @throws RemoteException
     * @throws ObjectNotFoundException
     */
    void setProformanceBase(String indexCode) throws RemoteException, ObjectNotFoundException;
}
