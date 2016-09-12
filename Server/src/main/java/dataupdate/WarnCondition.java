package dataupdate;

/**
 * Created by Daniel on 2016/9/12.
 */
public interface WarnCondition {
    /**
     * 判断条件
     *
     * @param obj
     * @return
     */
    boolean judgeCondition(Object obj);

    /**
     * 返回信息
     *
     * @return
     */
    String getMessage();
}
