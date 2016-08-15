package bl;

/**
 * Created by Daniel on 2016/8/15.
 */

import beans.PriceInfo;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import util.UnitType;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * 获得行情信息
 */
public interface MarketLogic extends Remote {
    /**
     * 返回对应基金代码、指定周期类型的所有数据
     *
     * @param code 基金代码
     * @param type 周期类型
     * @return
     * @throws RemoteException
     * @throws ObjectNotFoundException
     * @throws ParameterException
     */
    public List<PriceInfo> getPriceInfo(String code, UnitType type) throws RemoteException,
            ObjectNotFoundException, ParameterException;

    /**
     * @param code
     * @param type
     * @param counts 返回的数据的条数（自当前日期算）
     * @return
     * @throws RemoteException
     * @throws ObjectNotFoundException
     * @throws ParameterException
     */
    public List<PriceInfo> getPriceInfo(String code, UnitType type, int counts) throws
            RemoteException, ObjectNotFoundException, ParameterException;

    /**
     *
     * @param code
     * @param type
     * @param startDate 起始日期
     * @param endDate 结束日期
     * @return
     * @throws RemoteException
     * @throws ObjectNotFoundException
     * @throws ParameterException
     */
    public List<PriceInfo> getPriceInfo(String code, UnitType type, String startDate, String endDate)
            throws RemoteException, ObjectNotFoundException, ParameterException;
}

