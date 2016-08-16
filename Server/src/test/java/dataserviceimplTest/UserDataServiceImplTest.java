package dataserviceimplTest;

import com.google.gson.Gson;
import dataservice.UserDataService;
import dataserviceimpl.DataServiceController;
import entities.UserEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import startup.HibernateBoot;

/**
 * UserDataServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>08/16/2016</pre>
 */
public class UserDataServiceImplTest {
    UserDataService service;

    @Before
    public void before() throws Exception {
        HibernateBoot.init();
        service = DataServiceController.getUserDataService();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getUser(String username)
     */
    @Test
    public void testGetUser() throws Exception {
//TODO: Test goes here...
        UserEntity entity = service.getUser("yyf");
        System.out.println(new Gson().toJson(entity));
    }

    /**
     * Method: addUser(UserEntity username)
     */
    @Test
    public void testAddUser() throws Exception {
        UserEntity entity = new UserEntity();
        entity.setUsername("yyf");
        entity.setName("yyf");
        entity.setGender("male");
        entity.setPassword("123456");
        entity.setUsertype(util.UserType.MANAGER.toString());
        service.addUser(entity);
//TODO: Test goes here...
    }

    /**
     * Method: updateUser(UserEntity entity)
     */
    @Test
    public void testUpdateUser() throws Exception {
        UserEntity entity = new UserEntity();
        entity.setUsername("yyf");
        entity.setName("yyf");
        entity.setGender("male");
        entity.setPassword("123456");
        entity.setUsertype(util.UserType.MANAGER.toString());
        service.updateUser(entity);
    }


} 
