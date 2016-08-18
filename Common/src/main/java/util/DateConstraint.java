package util;

import java.util.Calendar;

/**
 * Created by Daniel on 2016/8/17.
 */
public interface DateConstraint {
    /**
     * 日期约束，判断两个日期的一些信息
     *
     * @param d1
     * @param d2
     * @return
     */
    boolean value(Calendar d1, Calendar d2);
}
