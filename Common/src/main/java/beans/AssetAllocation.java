package beans;

import java.io.Serializable;

/**
 * Created by Daniel on 2016/8/15.
 */
public class AssetAllocation implements Serializable {
    /**
     * 日期
     */
    public String date;
    /**
     * 股票市值（亿元）
     */
    public double stock_value;
    /**
     * 市值占比 (%)
     */
    public double stock_ratio;
    /**
     * 债券市值（亿元）
     */
    public double bond_value;
    /**
     * 债券占比(%)
     */
    public double bond_ratio;
    /**
     * 现金市值（亿元）
     */
    public double cash_value;
    /**
     * 现金占比（%）
     */
    public double cash_ratio;
    /**
     * 其他市值（亿元）
     */
    public double other_value;
    /**
     * 其他占比（%）
     */
    public double other_ratio;
    /**
     * 净资产（亿元）
     */
    public double net_value;
    /**
     * 资产总值（亿元）
     */
    public double total_value;
}
