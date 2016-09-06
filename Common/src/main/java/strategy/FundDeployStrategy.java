package strategy;

import beans.FundDeploy;

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
    public Map<String, List<Double>> getCodePrices(List<String> funds, int N,String startDate,String endDate) throws RemoteException ;

    /**
     * 获得小类费率矩阵
     * @param funds
     * @param N
     * @return
     * @throws RemoteException
     */
    public Map<String, List<Double>> getCodeFee(List<String> funds, int N) throws RemoteException;

    /**
     * 根据代码排名情况获得策略生成的组合
     * @param codes
     * @param N
     * @param window
     * @param hold
     * @return
     * @throws RemoteException
     */
    public FundDeploy calSharpe(List<String> codes, int N, int window, int hold, String startDate, String endDate) throws RemoteException;

    /**
     * 根据自选的基金计算小类配置结果
     * @param funds
     * @return
     */
    public FundDeploy CustomizedFundDeploy(List<String> funds,String startDate,String endDate) throws RemoteException;

    /**
     * 根据系统基金评级计算小类配置结果
     * @return
     */
    public FundDeploy DefaultFundDeploy(String sectorType) throws RemoteException;

}
