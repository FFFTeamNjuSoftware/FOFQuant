package util;

/**
 * Created by Daniel on 2016/8/15.
 */

/**
 * 用户类型
 */
public enum UserType {
    MANAGER("管理员"), NORMAL("普通用户");

    private UserType(String 管理员) {
    }

    public static UserType getStringValue(String userType) {
        if (userType.equalsIgnoreCase("MANAGER"))
            return MANAGER;
        else if (userType.equalsIgnoreCase("NORMAL"))
            return NORMAL;
        return null;
    }

}
