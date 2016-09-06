package bl.fof;

import beans.FOFHistoryInfo;
import beans.FOFQuickInfo;
import beans.PriceInfo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

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

    /**
     * 获得FOF组合内的所有基金
     * 按基金类型区分，map的key参照util.SectorType
     * 目前按照权益类和固定收益类，即key有000011与000012
     * @return
     * @throws RemoteException
     */
    Map<String,List<String>> getFundCodeInFOF() throws RemoteException;

    /**
     * 获得fof组合的历史数据
     * @return
     * @throws RemoteException
     */
    List<FOFHistoryInfo> getFOFHistoryInfo() throws RemoteException;

    /**
     * 获得最新的权重信息
     * first String is 权益类（000011），国定收益类（000012）
     * @return
     * @throws RemoteException
     */
    Map<String,Map<String,Double>> getNewestWeight () throws RemoteException;

}
