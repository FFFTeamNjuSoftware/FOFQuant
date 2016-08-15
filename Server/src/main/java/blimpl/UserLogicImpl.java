package blimpl;

import beans.UserInfo;
import beans.UserManageInfo;
import bl.UserLogic;
import exception.ObjectExistedException;
import exception.ObjectNotFoundException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by Daniel on 2016/8/15.
 */
public class UserLogicImpl extends UnicastRemoteObject implements UserLogic {

    private static UserLogic instance;

    private UserLogicImpl() throws RemoteException{
    }

    public static UserLogic getInstance() {
        if (instance == null)
            try {
                instance = new UserLogicImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        return instance;
    }

    @Override
    public UserInfo loginIn(String username, String password) throws RemoteException, ObjectNotFoundException {
        return null;
    }

    @Override
    public List<UserManageInfo> getAllUser() throws RemoteException {
        return null;
    }

    @Override
    public void updateUserInfo(UserManageInfo userManageInfo) throws RemoteException, ObjectNotFoundException {

    }

    @Override
    public void addUser(UserManageInfo userManageInfo) throws RemoteException, ObjectExistedException {

    }
}
