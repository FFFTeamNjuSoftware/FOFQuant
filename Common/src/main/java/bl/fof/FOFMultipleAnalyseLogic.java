package bl.fof;

import beans.FOFProfitAnalyse;
import beans.FundInFOFQuickInfo;
import beans.PerformanceAttribution;

import java.rmi.Remote;
import java.util.List;

/**
 * Created by Daniel on 2016/8/25.
 */
public interface FOFMultipleAnalyseLogic extends Remote {

    /**
     * 获得实时监控信息
     * @return
     */
    List<FundInFOFQuickInfo> getFOFFundQuickInfo();

    /**
     * 盈亏分析
     * @return
     */
    FOFProfitAnalyse getFOFProfitAnalyse();

    /**
     * 归因分析
     * @return
     */
    List<PerformanceAttribution> getPerformanceAttribution();

}
