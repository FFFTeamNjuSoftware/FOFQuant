package dataservice;

import entities.NetWorthEntity;
import exception.ObjectNotFoundException;
import util.UnitType;

import java.util.List;

/**
 * Created by Daniel on 2016/8/16.
 */
public interface MarketDataService {
    /**
     * 获得对应基金代码对应类型的净值数据
     * @param code
     * @param type
     * @return
     * @throws ObjectNotFoundException
     */
    public List<NetWorthEntity> getNetWorth(String code, UnitType type) throws
            ObjectNotFoundException;

    /**
     * 同上多了起止日期
     * @param code
     * @param type
     * @param startDate
     * @param endDate
     * @return
     * @throws ObjectNotFoundException
     */
    public List<NetWorthEntity> getNetWorth(String code, UnitType type, String startDate, String
            endDate) throws ObjectNotFoundException;

    /**
     * 同一多了返回数据的数量（日期近的数据）
     * @param code
     * @param type
     * @param resultCount
     * @return
     * @throws ObjectNotFoundException
     */
    public List<NetWorthEntity> getNetWorth(String code, UnitType type, int resultCount) throws
            ObjectNotFoundException;

}
