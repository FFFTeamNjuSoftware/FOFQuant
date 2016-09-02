package dataservice;

/**
 * Created by st0001 on 2016/9/1.
 */

import entities.FofAssetAllocationEntity;
import entities.FofHoldInfoEntity;
import entities.FofInfoEntity;
import entities.PositionChangeEntity;
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
     * 获得FOF的持仓信息
     *
     * @param fof_code
     * @return
     * @throws ObjectNotFoundException
     */
    List<FofHoldInfoEntity> getFofHoldInfos(String fof_code) throws ObjectNotFoundException;


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
}
