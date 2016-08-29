package strategy;

import java.util.List;

/**
 * Created by Seven on 16/8/29.
 */
public interface FundDeployStrategy {

    /**
     * 获得小类所选基金的日收盘价矩阵
     * @param funds
     * @return
     */
    public double[][] getFundsProfit(List<String> funds,String startDate,String endDate);

    /**
     * 获得小类所选基金的费率N*3矩阵
     * @param funds
     * @return
     */
    public double[][] getFundsFee(List<String> funds);

    /**
     * 获得回测结果,可指定日期
     * @param funds
     * @param startDate
     * @param endDate
     * @return
     */
    public List getWRpturn(List<String> funds,String startDate,String endDate);

    /**
     * 根据系统基金评级计算小类配置结果
     * @return
     */
    public List DefaultFundDeploy();

    /**
     * 根据自选的基金计算小类配置结果
     * @param funds
     * @return
     */
    public List CustomizedFundDeploy(List<String> funds);
}
