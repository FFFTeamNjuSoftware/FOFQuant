package dataupdate;

import beans.HoldingInfo;
import dataservice.FOFDataService;
import dataserviceimpl.DataServiceController;
import entities.FofHistoryInfoEntity;
import entities.FofHoldInfoEntity;
import entities.FofInfoEntity;

import java.util.List;

/**
 * Created by Daniel on 2016/9/2.
 */
public class FOFInfoUpdate {
    private FOFDataService fofDataService;
    private List<FofHoldInfoEntity> newFOFHoldInfos;
    private FofHistoryInfoEntity newFOFHistoryInfo;
    private FofInfoEntity fofInfoEntity;

    public FOFInfoUpdate() {
        fofDataService = DataServiceController.getFOFDataService();
    }

    /**
     * 更新FOF信息
     */
    public void updateFOFinfo() {
    }

    /**
     * 处理仓位变化信息
     */
    public void consumePositionChangeResuqest() {

    }

    /**
     * 更新基金的净值
     */
    public void updateNetWorth() {

    }


}
