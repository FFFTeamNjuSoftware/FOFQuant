package stubs.dataservice;

import dataservice.MarketDataService;
import entities.NetWorthEntity;
import exception.ObjectNotFoundException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 2016/8/16.
 */
public class MarketDataServiceStub implements MarketDataService {
    @Override
    public List<NetWorthEntity> getNetWorth(String code) throws ObjectNotFoundException {
        List<NetWorthEntity> netWorthEntityList = new ArrayList<>();
        NetWorthEntity netWorthEntity = new NetWorthEntity();
        netWorthEntity.setCode("000001");
        netWorthEntity.setDailyRise(0.01);
        netWorthEntity.setDate(new Date(2016 - 1900, 1 - 1, 1));
        netWorthEntity.setTotalWorth(10000.0);
        netWorthEntity.setUnitWorth(1000.0);
        netWorthEntityList.add(netWorthEntity);
        return netWorthEntityList;
    }

    @Override
    public List<NetWorthEntity> getNetWorth(String code, String startDate, String endDate) throws ObjectNotFoundException {
        List<NetWorthEntity> netWorthEntityList = new ArrayList<NetWorthEntity>();
        NetWorthEntity netWorthEntity = new NetWorthEntity();
        netWorthEntity.setCode("000001");
        netWorthEntity.setDailyRise(0.01);
        netWorthEntity.setDate(new Date(2016 - 1900, 1 - 1, 1));
        netWorthEntity.setTotalWorth(10000.0);
        netWorthEntity.setUnitWorth(1000.0);
        netWorthEntityList.add(netWorthEntity);
        return netWorthEntityList;
    }

}
