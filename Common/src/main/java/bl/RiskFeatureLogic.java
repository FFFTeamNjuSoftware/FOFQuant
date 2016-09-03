package bl;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Daniel on 2016/8/15.
 */

/**
 * 风险特征
 */
public interface RiskFeatureLogic extends Remote {
    /**
     * 获得标准差
     * @param code
     * @return
     * @throws RemoteException
     */
    public double getStandardDeviation(String code) throws RemoteException;

    /**
     * 获得beta系数
     * @param code
     * @return
     * @throws RemoteException
     */
    public double getBeta(String code) throws RemoteException;

    /**
     * 获得詹森指数
     * @param code
     * @return
     * @throws RemoteException
     */
    public double getJansen(String code) throws RemoteException;

    /**
     * 获得特雷洛指数
     * @param code
     * @return
     * @throws RemoteException
     */
    public double getTreynor(String code) throws RemoteException;

    /**
     * 获得夏普指数
     * @param code
     * @return
     * @throws RemoteException
     */
    public double getSharpe(String code) throws RemoteException;

    /**
     * 年化波动率
     * @param code
     * @return
     * @throws RemoteException
     */
    double yearWaveRate(String code) throws RemoteException;
}
