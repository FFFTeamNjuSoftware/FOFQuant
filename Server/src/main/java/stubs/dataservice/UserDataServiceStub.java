package stubs.dataservice;

import dataservice.UserDataService;
import entities.UserEntity;
import exception.ObjectExistedException;
import exception.ObjectNotFoundException;

/**
 * Created by Daniel on 2016/8/16.
 */
public class UserDataServiceStub implements UserDataService {
    @Override
    public UserEntity getUser(String username) throws ObjectNotFoundException {
        return null;
    }

    @Override
    public void addUser(UserEntity username) throws ObjectExistedException {

    }

    @Override
    public void updateUser(UserEntity entity) throws ObjectNotFoundException {

    }
}
