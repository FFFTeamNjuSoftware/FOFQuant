package dataservice;

import entities.UserEntity;
import exception.ObjectExistedException;
import exception.ObjectNotFoundException;

/**
 * Created by Daniel on 2016/8/16.
 */
public interface UserDataService {
    /**
     * 获得user信息
     *
     * @param username
     * @return
     * @throws ObjectNotFoundException
     */
    public UserEntity getUser(String username) throws ObjectNotFoundException;

    /**
     * 添加用户
     *
     * @param username
     * @throws ObjectExistedException
     */
    public void addUser(String username) throws ObjectExistedException;

    /**
     * 更新用户信息
     *
     * @param entity
     * @throws ObjectNotFoundException
     */
    public void updateUser(UserEntity entity) throws ObjectNotFoundException;

}
