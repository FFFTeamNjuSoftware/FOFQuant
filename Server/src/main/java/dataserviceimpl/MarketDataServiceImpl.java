package dataserviceimpl;

import dataservice.MarketDataService;
import entities.NetWorthEntity;
import exception.ObjectNotFoundException;
import util.UnitType;

import java.util.List;

/**
 * Created by Daniel on 2016/8/16.
 */
public class MarketDataServiceImpl implements MarketDataService {

    protected MarketDataServiceImpl() {
    }

    @Override
    public List<NetWorthEntity> getNetWorth(String code, UnitType type) throws ObjectNotFoundException {
        return null;
    }

    @Override
    public List<NetWorthEntity> getNetWorth(String code, UnitType type, String startDate, String endDate) throws ObjectNotFoundException {
        return null;
    }

    @Override
    public List<NetWorthEntity> getNetWorth(String code, UnitType type, int resultCount) throws ObjectNotFoundException {
        return null;
    }
}
