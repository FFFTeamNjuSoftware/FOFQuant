package dataserviceimpl;

import dataservice.UserDataService;
import entities.UserEntity;
import exception.ObjectExistedException;
import exception.ObjectNotFoundException;

/**
 * Created by Daniel on 2016/8/16.
 */
public class UserDataServiceImpl implements UserDataService {
    protected UserDataServiceImpl() {
    }

    @Override
    public UserEntity getUser(String username) throws ObjectNotFoundException {
        return null;
    }

    @Override
    public void addUser(String username) throws ObjectExistedException {

    }

    @Override
    public void updateUser(UserEntity entity) throws ObjectNotFoundException {

    }
}
