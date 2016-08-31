package beans;

import java.io.Serializable;

/**
 * Created by Daniel on 2016/8/15.
 */

/**
 * 基金的基本信息
 */
public class FundInfo implements Serializable {
    /**
     * 基金简称
     */
    public String simple_name;
    /**
     * 基金全称
     */
    public String full_name;
    /**
     * 基金类型
     */
    public String fund_type;
    /**
     * 投资类型
     */
    public String invest_type;
    /**
     * 建立日期
     */
    public String establish_date;
    /**
     * 建立规模（亿份）
     */
    public double establish_scale;
    /**
     * 规模（亿份）
     */
    public double scale;
    /**
     * 管理费（%）
     */
    public double manage_fee;
    /**
     * 比较基准
     */
    public String compare_base;
    /**
     * 投资目标
     */
    public String invest_target;
    /**
     * 投资策略
     */
    public String invest_strategy;
    /**
     * 风险收益特性
     */
    public String risk_feature;
    /**
     * 基金评级
     */
    public int rank;

}
