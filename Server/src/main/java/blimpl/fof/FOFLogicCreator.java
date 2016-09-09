package blimpl.fof;

import bl.fof.*;

/**
 * Created by Daniel on 2016/8/28.
 */
public interface FOFLogicCreator {
    FOFAssetAllocationLogic getFofAssetAllocationLogic();

    FOFBaseInfoLogic getFOFBaseInfoLogic();

    FOFGenerateLogic getFOFGenerateLogic();

    FOFPerformanceAttributionLogic getFOFPerformanceAttributionLogic();

    FOFProfitAnalyseLogic getFOFProfitAnalyseLogic();

    FOFProfitStatisticsLogic getFOFPrifitStatisticsLogic();

    FOFRealTimeMonitorLogic getFOFRealTimeMonitorLogic();

    WarnLogLogic getWarnLogic();
}
