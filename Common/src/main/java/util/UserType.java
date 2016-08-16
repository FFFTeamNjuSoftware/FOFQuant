package util;

/**
 * Created by Daniel on 2016/8/15.
 */

/**
 * 用户类型
 */
public enum UserType {
    MANAGER, NORMAL;

    public static UserType getStringValue(String userType) {
        if (userType.equalsIgnoreCase("MANAGER"))
            return MANAGER;
        else if (userType.equalsIgnoreCase("NORMAL"))
            return NORMAL;
        return null;
    }
}
