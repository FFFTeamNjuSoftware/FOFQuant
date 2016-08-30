package strategy;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

/**
 * Created by Seven on 16/8/29.
 */
public interface FundDeployStrategy {
    /**
     * 获得小类收盘价矩阵
     * @param funds
     * @param N
     * @return
     * @throws RemoteException
     */
    public Map<String, List<Double>> getCodePrices(List<String> funds, int N) throws RemoteException ;

    /**
     * 获得小类费率矩阵
     * @param funds
     * @param N
     * @return
     * @throws RemoteException
     */
    public Map<String, List<Double>> getCodeFee(List<String> funds, int N) throws RemoteException;

    /**
     * 根据代码排名情况获得权重分配
     * @param codeRank
     * @param N
     * @param window
     * @param hold
     * @return
     * @throws RemoteException
     */
    public void AdjustiveFundDeploy(List<String> codeRank, int N, int window, int hold) throws RemoteException;

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
    public List DefaultFundDeploy() throws RemoteException;

    /**
     * 根据自选的基金计算小类配置结果
     * @param funds
     * @return
     */
    public List CustomizedFundDeploy(List<String> funds);
}
