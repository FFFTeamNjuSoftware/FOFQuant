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
     * @return
     * @throws ObjectNotFoundException
     */
    public List<NetWorthEntity> getNetWorth(String code) throws
            ObjectNotFoundException;

    /**
     * 同上多了起止日期
     * @param code
     * @param startDate
     * @param endDate
     * @return
     * @throws ObjectNotFoundException
     */
    public List<NetWorthEntity> getNetWorth(String code,  String startDate, String
            endDate) throws ObjectNotFoundException;

    NetWorthEntity getNewestNetWorth(String code) throws ObjectNotFoundException;

}
