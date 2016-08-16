package beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Daniel on 2016/8/15.
 */

/**
 * 持有信息
 */
public class HoldingInfo implements Serializable {
    /**
     * 日期
     */
    public String date;
    /**
     * 持有信息
     */
    public List<HoldingUnit> items;
}
