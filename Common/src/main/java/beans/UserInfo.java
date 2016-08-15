package beans;

/**
 * Created by Daniel on 2016/8/15.
 */

import util.UserType;

import java.io.Serializable;

/**
 * 用户信息
 */
public class UserInfo implements Serializable {
    public String name;
    public String gender;
    public UserType userType;
}
