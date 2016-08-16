package blimpl;

import beans.UserInfo;
import beans.UserManageInfo;
import bl.UserLogic;
import dataservice.UserDataService;
import dataserviceimpl.DataServiceController;
import entities.UserEntity;
import exception.AuthorityException;
import exception.ObjectExistedException;
import exception.ObjectNotFoundException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Daniel on 2016/8/15.
 */
public class UserLogicImpl extends UnicastRemoteObject implements UserLogic {

    private static UserLogic instance;
    private UserDataService userService;

    private UserLogicImpl() throws RemoteException {
        userService = DataServiceController.getUserDataService();
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
    public UserInfo loginIn(String username, String password) throws RemoteException,
            ObjectNotFoundException, AuthorityException {
        UserEntity entity = userService.getUser(username);
        if (!entity.getPassword().equals(password)) {
            throw new AuthorityException("password wrong");
        }
        return Converter.convertUserInfo(entity);
    }

    @Override
    public List<UserManageInfo> getAllUser() throws RemoteException {
        return userService.getAllUser().stream().map(Converter::convertUserManageInfo)
                .collect(Collectors.toList());
    }

    @Override
    public void updateUserInfo(UserManageInfo userManageInfo) throws RemoteException, ObjectNotFoundException {
        userService.updateUser(Converter.convertUserEntity(userManageInfo));
    }

    @Override
    public void addUser(UserManageInfo userManageInfo) throws RemoteException, ObjectExistedException {
        userService.addUser(Converter.convertUserEntity(userManageInfo));
    }
}
