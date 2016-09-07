package dataservice;

/**
 * Created by st0001 on 2016/9/1.
 */

import entities.*;
import exception.ObjectNotFoundException;

import java.util.List;

/**
 * FOF的数据服务
 */
public interface FOFDataService {
    /**
     * @param fof_code
     * @return
     * @throws ObjectNotFoundException
     */
    List<FofAssetAllocationEntity> getFOFAssetAllocation(String fof_code) throws ObjectNotFoundException;

    /**
     * 获得FOF的基本信息
     *
     * @param fof_code
     * @return
     * @throws ObjectNotFoundException
     */
    FofInfoEntity getFofInfoEntity(String fof_code) throws ObjectNotFoundException;

    /**
     * 获得fof组合的历史信息
     *
     * @param fof_code
     * @return
     * @throws ObjectNotFoundException
     */
    List<FofHistoryInfoEntity> getFofHistoryInfos(String fof_code) throws ObjectNotFoundException;

    /**
     * 获得最新的FOF历史信息
     *
     * @param fof_code
     * @return
     * @throws ObjectNotFoundException
     */
    FofHistoryInfoEntity getNewestHistoryInfo(String fof_code) throws ObjectNotFoundException;

    /**
     * 获得FOF的持仓信息
     *
     * @param fof_code
     * @return
     * @throws ObjectNotFoundException
     */
    List<FofHoldInfoEntity> getFofHoldInfos(String fof_code) throws ObjectNotFoundException;

    /**
     * 获得FOF的调整请求
     *
     * @param fof_code
     * @return
     * @throws ObjectNotFoundException
     */
    List<PositionChangeEntity> getPositionChangeRequest(String fof_code) throws ObjectNotFoundException;

    /**
     * 获得FOF的开仓信息
     *
     * @param fof_code
     * @return
     */
    List<FofEstablishInfoEntity> getFofEstablishInfo(String fof_code) throws ObjectNotFoundException;

    /**
     * 获得最新FOF的持仓信息
     *
     * @param fof_code
     * @return
     * @throws ObjectNotFoundException
     */
    List<FofHoldInfoEntity> getNewestFofHoldInfos(String fof_code) throws ObjectNotFoundException;


    /**
     * 获得FOF的持仓信息
     *
     * @param fof_code
     * @return
     * @throws ObjectNotFoundException
     */
    List<FofHoldInfoEntity> getFofHoldInfos(String fof_code, String startDate, String endDate) throws
            ObjectNotFoundException;

    /**
     * 获得仓位变动信息
     *
     * @param fof_code
     * @return
     * @throws ObjectNotFoundException
     */
    List<PositionChangeEntity> getPositionChange(String fof_code) throws ObjectNotFoundException;

    /**
     * 保存调仓请求
     * @param entities
     * @throws ObjectNotFoundException
     */
    void savePositionChangeEntity(List<PositionChangeEntity> entities) throws
            ObjectNotFoundException;
}
