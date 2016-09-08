package util;

/**
 * Created by Daniel on 2016/8/26.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 业绩基准
 */
public class FOFUtilInfo {
    /**
     * 业绩基准
     */
    public static Map<String, String> performanceBaseInfo;
    /**
     * 展示信息的单位
     */
    public static final double VALUE_UNIT = 100000000, NUM_UNIT = 1000000, PROFIT_UNIT = 1000000;
    /**
     * FOF的代码
     */
    public static final String FOF_CODE = "F000001";

    static {
        performanceBaseInfo = new HashMap<>();
        performanceBaseInfo.put("沪深300", "I000300");
        performanceBaseInfo.put("上证综指", "I000001");
        performanceBaseInfo.put("上证基金指数", "I000011");
    }
}
