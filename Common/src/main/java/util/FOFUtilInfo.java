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
     * FOF的代码
     */

    static {
        performanceBaseInfo = new HashMap<>();
        performanceBaseInfo.put("沪深300", "I000300");
        performanceBaseInfo.put("上证综指", "I000001");
        performanceBaseInfo.put("上证基金指数", "I000011");
    }
}
