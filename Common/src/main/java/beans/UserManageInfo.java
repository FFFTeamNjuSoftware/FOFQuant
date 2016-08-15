package beans;

import util.UserType;

import java.io.Serializable;

/**
 * Created by Daniel on 2016/8/15.
 */
public class UserManageInfo implements Serializable {
    public String name;
    public String gender;
    public String username;
    public String password;
    public UserType userType;
}
