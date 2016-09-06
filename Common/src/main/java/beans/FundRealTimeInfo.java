package beans;

/**
 * Created by Daniel on 2016/8/30.
 */

import java.io.Serializable;

/**
 * 基金的实时数据
 */
public class FundRealTimeInfo implements Serializable {
    /**
     * 基金代码
     */
    public String fundcode;
    /**
     * 基金名字
     */
    public String name;
    /**
     * 基准日期
     */
    public String jzrq;
    /**
     * 昨日收盘净值
     */
    public double dwjz;
    /**
     * 估算净值
     */
    public double gsz;
    /**
     * 估算涨幅
     */
    public double gszzl;
    /**
     * 时间 "YYYY-MM-DD HH-MM"
     */
    public String gztime;
}
