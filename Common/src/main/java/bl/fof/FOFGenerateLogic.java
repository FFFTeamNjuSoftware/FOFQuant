package bl.fof;

import util.SectorType;

import java.io.Serializable;

/**
 * Created by Daniel on 2016/8/26.
 */
public interface FOFGenerateLogic extends Serializable {
    /**
     * 返回大类配置的权重比
     *
     * @return
     */
    double[] getLargeClassConfiguration();


    /**
     * 返回小类配置权重比
     * @return
     */
    double[][] getSmallClassConfiguration();

    /**
     * 返回小类配置权重比
     * @param fundNum 各类型的基金数量
     * @return
     */
    double[][] getSmallClassConfiguration(int ... fundNum);
}
