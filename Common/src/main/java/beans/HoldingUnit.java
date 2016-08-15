package beans;

import java.io.Serializable;

/**
 * Created by Daniel on 2016/8/15.
 */

/**
 * 持有单位
 */
public class HoldingUnit implements Serializable {
    /**
     * 代码
     */
    public String code;
    /**
     * 名字
     */
    public String name;
    /**
     * 持有量（万股/万张，其中行业的持有情况没有这个值）
     */
    public double holdNum;
    /**
     * 市值（万元）
     */
    public double value;
    /**
     * 占净值比
     */
    public double ratio;
}
