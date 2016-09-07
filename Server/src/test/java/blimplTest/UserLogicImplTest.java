package blimplTest;

import beans.UserInfo;
import beans.UserManageInfo;
import bl.UserLogic;
import blimpl.BLController;
import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import startup.HibernateBoot;

/**
 * UserLogicImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>08/20/2016</pre>
 */
public class UserLogicImplTest {
    UserLogic userLogic;

    @Before
    public void before() throws Exception {
        HibernateBoot.init();
        userLogic = BLController.getUserLogic();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getInstance()
     */
    @Test
    public void testGetInstance() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: loginIn(String username, String password)
     */
    @Test
    public void testLoginIn() throws Exception {
        UserInfo userInfo=userLogic.loginIn("yyf","123456");
        System.out.println(new Gson().toJson(userInfo));
    }

    /**
     * Method: getAllUser()
     */
    @Test
    public void testGetAllUser() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: updateUserInfo(UserManageInfo userManageInfo)
     */
    @Test
    public void testUpdateUserInfo() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: addUser(UserManageInfo userManageInfo)
     */
    @Test
    public void testAddUser() throws Exception {
        UserManageInfo userManageInfo=userLogic.getAllUser().get(0);
        userLogic.deleteUser(userManageInfo);
    }


} 
