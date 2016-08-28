package bl.fof;

import util.SectorType;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Map;

/**
 * Created by Daniel on 2016/8/26.
 */
public interface FOFGenerateLogic extends Serializable {
    /**
     * 返回大类配置的权重比
     *
     * @return
     */
    Map<String, Double> getLargeClassConfiguration()throws RemoteException;




    /**
     * 返回小类配置权重比
     *
     * @return
     */
    Map<String, Map<String, Double>> getSmallClassConfiguration()throws RemoteException;
}
