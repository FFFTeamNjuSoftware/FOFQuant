package stubs.dataservice;

import beans.UserManageInfo;
import dataservice.UserDataService;
import entities.UserEntity;
import exception.ObjectExistedException;
import exception.ObjectNotFoundException;

import java.util.List;

/**
 * Created by Daniel on 2016/8/16.
 */
public class UserDataServiceStub implements UserDataService {
    @Override
    public UserEntity getUser(String username) throws ObjectNotFoundException {
        UserEntity userEntity=new UserEntity();
        userEntity.setName("Buffett");
        userEntity.setPassword("12345678");
        userEntity.setGender("男");
        userEntity.setUsername("Jack");
        userEntity.setUsertype("Corporation");
        return userEntity;
    }

    @Override
    public void addUser(UserEntity username) throws ObjectExistedException {

    }

    @Override
    public void updateUser(UserEntity entity) throws ObjectNotFoundException {

    }

    @Override
    public void deleteUser(UserManageInfo entity) throws ObjectNotFoundException {

    }

    @Override
    public List<UserEntity> getAllUser() {
        return null;
    }
}
