package bl.fof;

import beans.FOFProfitAnalyse;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import util.TimeType;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Daniel on 2016/8/26.
 */

/**
 * 盈亏分析
 */
public interface FOFProfitAnalyseLogic extends Remote {
    /**
     * 设置区间开始时间
     *
     * @throws ParameterException
     */
    void setStartDate() throws ParameterException, RemoteException;

    /**
     * 设置区间结束时间
     *
     * @throws ParameterException
     */
    void setEndDate() throws ParameterException, RemoteException;

    /**
     * 设置业绩基准，大盘指数等
     *
     * @param indexCode
     * @throws RemoteException
     * @throws ObjectNotFoundException
     */
    void setProformanceBase(String indexCode) throws RemoteException, ObjectNotFoundException;

    FOFProfitAnalyse getFOFProfitAnalyse(TimeType timeType) throws RemoteException;
}
