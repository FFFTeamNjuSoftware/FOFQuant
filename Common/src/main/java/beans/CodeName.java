package beans;

/**
 * Created by Daniel on 2016/8/15.
 */

import java.io.Serializable;

/**
 * 基金的代码和名字，用户查找功能里
 */
public class CodeName implements Serializable{
    /**
     * 基金代码
     */
    public String code;
    /**
     * 基金名字
     */
    public String name;
}
