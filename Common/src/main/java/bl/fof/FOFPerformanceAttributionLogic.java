package bl.fof;

import beans.PerformanceAttribution;
import exception.ObjectNotFoundException;
import exception.ParameterException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Daniel on 2016/8/26.
 */

/**
 * 业绩归因
 */
public interface FOFPerformanceAttributionLogic extends Remote {
    /**
     * 设置区间开始时间
     *
     * @throws ParameterException
     */
    void setStartDate(String startDate) throws ParameterException, RemoteException;

    /**
     * 设置区间结束时间
     *
     * @throws ParameterException
     */
    void setEndDate(String endDate) throws ParameterException, RemoteException;

    /**
     * 设置业绩基准，大盘指数等
     *
     * @param indexCode
     * @throws RemoteException
     * @throws ObjectNotFoundException
     */
    void setProformanceBase(String indexCode) throws RemoteException, ObjectNotFoundException;

    List<PerformanceAttribution> getPerformanceAttribution() throws RemoteException;
}
