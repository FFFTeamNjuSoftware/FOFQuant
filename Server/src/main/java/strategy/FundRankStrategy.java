package strategy;

/**
 * Created by Seven on 16/8/19.
 * 基金评级策略
 * 风险指标R+收益指标E=风险收益指标
 * 基金公司指标+条件阈值=条件指标
 * 用条件指标过滤风险收益指标->排名指标
 */
public class FundRankStrategy {

    /**
     * 获得基金月复权单位净值增长率Rp
     * @param fundcode
     * @return
     */
    public double getFundMonthGrowthRate(String fundcode){
        return 0.0;
    }

    /**
     * 获得前n个月的超额收益Rn
     * Rn=Rp-Rf(Rf设为0.2996%,年化3.5%),本模型中n=12
     * @param fundcode
     * @param n
     * @param rf 月无风险收益率
     * @return
     */
    public double getFundProfit(String fundcode,int n,double rf){
        return 0.0;
    }

    /**
     *  获得前n周的累计负超额收益Rd
     * @param fundcode
     * @param n
     * @return
     */
    public double getFundNegativeProfit(String fundcode,int n){
        return 0.0;
    }

    /**
     * 收益指标E
     * @param fundcode
     * @param e 收益指标系数
     * @param a 收益加权系数
     * @return
     */
    public double getIndexE(String fundcode,double e,double a){
        return 0.0;

    }

    /**
     * 风险指标R
     * @param fundcode
     * @param d 风险指标系数
     * @return
     */
    public double getIndexR(String fundcode,String d){
        return 0.0;
    }

    /**
     * 风险收益指标RE
     * @param fundcode
     * @return
     */
    public double getIndexRE(String fundcode){
        return 0.0;
    }

    /**
     * 单只基金N周收益排名百分比Pj
     * @param fundcode
     * @param n
     * @return
     */
    public double getFundRankPercentage(String fundcode,int n){
        return 0.0;
    }

    /**
     * 基金公司旗下基金收益排名百分比均值Pk
     * @param fundcode
     * @param n
     * @return
     */
    public double getCompanyRankPercentageAve(String fundcode,int n){
        return 0.0;
    }

    /**
     * 条件指标D
     * @param fundcode
     * @param b
     * @return
     */
    public double getIndexD(String fundcode,double b){
        return 0.0;
    }

    /**
     * 最终的排名指标RI
     * @param fundcode
     * @return
     */
    public double getRankIndex(String fundcode){
        return 0.0;
    }
}
