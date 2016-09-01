package bl;

import beans.InvestStyleAnalyse;
import beans.RiskProfitIndex;
import exception.ObjectNotFoundException;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Daniel on 2016/8/15.
 */

/**
 * 收益特征
 */
public interface ProfitFeatureLogic extends Remote {
    /**
     * 获得alpha值
     *
     * @param code
     * @return
     * @throws RemoteException
     */
    public double getAlpha(String code) throws RemoteException, ObjectNotFoundException;

    /**
     * 获得平均收益率
     *
     * @param code
     * @return
     * @throws RemoteException
     */
    public double aveProfitRate(String code) throws RemoteException, ObjectNotFoundException;

    /**
     * 获得风险收益率
     *
     * @param code
     * @return
     * @throws RemoteException
     */
    public double riskProfitRate(String code) throws RemoteException, ObjectNotFoundException;

    /**
     * 获得几何平均收益率
     *
     * @param code
     * @return
     * @throws RemoteException
     */
    public double getEnsembleAveProfitRate(String code) throws RemoteException, ObjectNotFoundException;

    /**
     * 获得风险收益指数
     * @param code
     * @return
     * @throws RemoteException
     * @throws ObjectNotFoundException
     */
    RiskProfitIndex getRiskProfitIndex(String code) throws RemoteException,ObjectNotFoundException;

    /**
     * 获得投资风格分析
     * @param code
     * @return
     * @throws RemoteException
     * @throws ObjectNotFoundException
     */
    InvestStyleAnalyse getInvestStyleAnalyse(String code) throws RemoteException,
            ObjectNotFoundException;
}
