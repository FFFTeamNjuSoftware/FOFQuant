package util;

/**
 * Created by Daniel on 2016/8/20.
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基金分类类别的类
 */
public class SectorType {
    /**
     * 全部开放型
     */
    public static final String OPEN_TYPE = "000001";
    /**
     * 股票型
     */
    public static final String STOCK_TYPE = "000002";
    /**
     * 债券
     */
    public static final String BOND_TYPE = "000003";
    /**
     * 混合
     */
    public static final String MIX_TYPE = "000004";
    /**
     * 保本
     */
    public static final String GUARANTEED_TYPE = "000005";
    /**
     * 指数
     */
    public static final String INDEX_TYPE = "000006";
    /**
     * 货币市场型
     */
    public static final String MONEY_MARKET_TYPE = "000007";
    /**
     * QDII
     */
    public static final String QDII_TYPE = "000008";
    /**
     * LOF
     */
    public static final String LOF_TYPE = "000009";
    /**
     * ETF
     */
    public static final String ETF_TYPE = "000010";
    /**
     * 固定收益类
     */
    public static final String FIX_PROFIT_TYPE = "000011";
    /**
     * 权益类
     */
    public static final String RIGHTS_TYPE = "000012";
    /**
     * 其他类
     */
    public static final String OTHER_TYPE = "000013";

    public static final Map<String, List<String>> COMPONENT_INFO = new HashMap<>();

    static {
        COMPONENT_INFO.put(FIX_PROFIT_TYPE, Arrays.asList(BOND_TYPE, MONEY_MARKET_TYPE,
                GUARANTEED_TYPE));
        COMPONENT_INFO.put(RIGHTS_TYPE, Arrays.asList(STOCK_TYPE, MIX_TYPE));
        COMPONENT_INFO.put(OTHER_TYPE, Arrays.asList(INDEX_TYPE, QDII_TYPE, LOF_TYPE, ETF_TYPE));
    }
}
