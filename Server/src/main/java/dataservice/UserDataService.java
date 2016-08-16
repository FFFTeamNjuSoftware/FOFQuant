package dataservice;

import entities.UserEntity;
import exception.ObjectExistedException;
import exception.ObjectNotFoundException;

import java.util.List;

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
     * 获得所有用户
     *
     * @return
     */
    public List<UserEntity> getAllUser();

    /**
     * 添加用户
     *
     * @param username
     * @throws ObjectExistedException
     */
    public void addUser(UserEntity username) throws ObjectExistedException;

    /**
     * 更新用户信息
     *
     * @param entity
     * @throws ObjectNotFoundException
     */
    public void updateUser(UserEntity entity) throws ObjectNotFoundException;

}
