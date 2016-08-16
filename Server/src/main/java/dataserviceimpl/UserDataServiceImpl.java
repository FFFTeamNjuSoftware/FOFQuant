package dataserviceimpl;

import dataservice.UserDataService;
import entities.UserEntity;
import exception.ObjectExistedException;
import exception.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.StaleStateException;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import startup.HibernateBoot;

import java.util.List;

/**
 * Created by Daniel on 2016/8/16.
 */
public class UserDataServiceImpl implements UserDataService {
    protected UserDataServiceImpl() {
    }

    @Override
    public UserEntity getUser(String username) throws ObjectNotFoundException {
        Session se = HibernateBoot.openSession();
        List re = se.createQuery("from UserEntity where username=:username").setString("username",
                username).list();
        se.close();
        if (re == null || re.size() == 0)
            throw new ObjectNotFoundException("User " + username + " not found");
        return (UserEntity) re.get(0);
    }

    @Override
    public void addUser(UserEntity username) throws ObjectExistedException {
        Session se = HibernateBoot.openSession();
        Transaction tra = se.beginTransaction();
        try {
            se.save(username);
            tra.commit();
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            throw new ObjectExistedException("username " + username.getUsername() + " existed");
        } finally {
            se.close();
        }
    }

    @Override
    public void updateUser(UserEntity entity) throws ObjectNotFoundException {
        Session se = HibernateBoot.openSession();
        Transaction tra = se.beginTransaction();
        try {
            se.update(entity);
            tra.commit();
        } catch (StaleStateException e) {
            e.printStackTrace();
            throw new ObjectNotFoundException("user not found");
        } finally {
            se.close();
        }
    }
}
