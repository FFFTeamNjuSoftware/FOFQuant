package stubs.logic;

import beans.UserInfo;
import beans.UserManageInfo;
import bl.UserLogic;
import exception.AuthorityException;
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
    public UserInfo loginIn(String username, String password) throws RemoteException, ObjectNotFoundException, AuthorityException {

        if (username.equals("Buffett") && password.equals("123456")) {
            UserInfo userInfo = new UserInfo();
            userInfo.gender = "male";
            userInfo.name = "Buffett";
            userInfo.userType = UserType.NORMAL;
            return userInfo;
        } else if (username.equals("xiaoming") && password.equals("123456")) {
            UserInfo userInfo = new UserInfo();
            userInfo.gender = "male";
            userInfo.name = "xiaoming";
            userInfo.userType = UserType.MANAGER;
            return userInfo;
        }
        throw new ObjectNotFoundException("username or password wrong");
    }

    @Override
    public List<UserManageInfo> getAllUser() throws RemoteException {
        List<UserManageInfo> userManageInfoList = new ArrayList<UserManageInfo>();
        UserManageInfo userManageInfo = new UserManageInfo();
        userManageInfo.gender = "male";
        userManageInfo.name = "Buffett";
        userManageInfo.username = "Jack";
        userManageInfo.password = "123456";
        userManageInfo.userType = UserType.NORMAL;

        UserManageInfo userManageInfo1 = new UserManageInfo();
        userManageInfo.gender = "male";
        userManageInfo.name = "xiaoming";
        userManageInfo.username = "xiaoming";
        userManageInfo.password = "123456";
        userManageInfo.userType = UserType.MANAGER;

        userManageInfoList.add(userManageInfo);
        userManageInfoList.add(userManageInfo1);
        return userManageInfoList;
    }

    @Override
    public void updateUserInfo(UserManageInfo userManageInfo) throws RemoteException, ObjectNotFoundException {

    }

    @Override
    public void addUser(UserManageInfo userManageInfo) throws RemoteException, ObjectExistedException {

    }

    @Override
    public void deleteUser(UserManageInfo userManageInfo) throws RemoteException, ObjectNotFoundException {
    }
}
