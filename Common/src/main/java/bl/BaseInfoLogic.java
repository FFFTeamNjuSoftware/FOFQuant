package bl;

/**
 * Created by Daniel on 2016/8/15.
 */

import beans.CodeName;
import beans.FundInfo;
import exception.ObjectNotFoundException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * 基础信息接口
 */
public interface BaseInfoLogic extends Remote {
    /**
     * 获得所有基金代码
     *
     * @return
     * @throws RemoteException
     */
    public List<String> getFundCodes() throws RemoteException;

    /**
     * 模糊查找
     *
     * @param keyword
     * @return
     * @throws RemoteException
     */
    public List<CodeName> fuzzySearch(String keyword) throws RemoteException;

    /**
     * 获得基金的基本信息
     *
     * @return
     * @throws RemoteException
     * @throws ObjectNotFoundException
     */
    public FundInfo getFundBaseInfo(String code) throws RemoteException, ObjectNotFoundException;
}