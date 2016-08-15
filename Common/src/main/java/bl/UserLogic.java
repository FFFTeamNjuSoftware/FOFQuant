package bl;

import beans.UserInfo;
import beans.UserManageInfo;
import exception.ObjectExistedException;
import exception.ObjectNotFoundException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Daniel on 2016/8/15.
 */

/**
 * 提供用户信息的接口
 */
public interface UserLogic extends Remote {
    /**
     * 登录方法
     * 成功后返回用户信息
     *
     * @param username 用户名
     * @param password 密码
     * @return
     * @throws RemoteException
     * @throws ObjectNotFoundException
     */
    public UserInfo loginIn(String username, String password) throws RemoteException,
            ObjectNotFoundException;

    /**
     * 获得用户的所有信息，管理员使用
     *
     * @return
     * @throws RemoteException
     */
    public List<UserManageInfo> getAllUser() throws RemoteException;

    /**
     * 更新用户信息
     *
     * @param userManageInfo
     * @throws RemoteException
     * @throws ObjectNotFoundException
     */
    public void updateUserInfo(UserManageInfo userManageInfo) throws RemoteException,
            ObjectNotFoundException;

    /**
     * 添加用户
     *
     * @param userManageInfo
     * @throws RemoteException
     * @throws ObjectExistedException
     */
    public void addUser(UserManageInfo userManageInfo) throws RemoteException, ObjectExistedException;

}
