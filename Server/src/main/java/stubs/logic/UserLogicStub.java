package stubs.logic;

import beans.UserInfo;
import beans.UserManageInfo;
import bl.UserLogic;
import exception.ObjectExistedException;
import exception.ObjectNotFoundException;
import util.UserType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 2016/8/15.
 */
public class UserLogicStub extends UnicastRemoteObject implements UserLogic {
    public UserLogicStub() throws RemoteException {

    }

    @Override
    public UserInfo loginIn(String username, String password) throws RemoteException, ObjectNotFoundException {
        UserInfo userInfo=new UserInfo();
        userInfo.gender="男";
        userInfo.name="Buffett";
        userInfo.userType= UserType.NORMAL;
        return userInfo;
    }

    @Override
    public List<UserManageInfo> getAllUser() throws RemoteException {
        List<UserManageInfo>  userManageInfoList= new ArrayList<UserManageInfo>();
        UserManageInfo userManageInfo=new UserManageInfo();
        userManageInfo.gender="男";
        userManageInfo.name="Buffett";
        userManageInfo.username="Jack";
        userManageInfo.userType=UserType.MANAGER;
        userManageInfoList.add(userManageInfo);
        return userManageInfoList;
    }

    @Override
    public void updateUserInfo(UserManageInfo userManageInfo) throws RemoteException, ObjectNotFoundException {

    }

    @Override
    public void addUser(UserManageInfo userManageInfo) throws RemoteException, ObjectExistedException {

    }
}
