package dataserviceimpl;

import dataservice.MarketDataService;
import entities.NetWorthEntity;
import exception.ObjectNotFoundException;
import org.hibernate.Session;
import startup.HibernateBoot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 2016/8/16.
 */
public class MarketDataServiceImpl implements MarketDataService {

    protected MarketDataServiceImpl() {
    }

    @Override
    public List<NetWorthEntity> getNetWorth(String code) throws ObjectNotFoundException {
        Session se = HibernateBoot.openSession();
        List re = se.createQuery("from NetWorthEntity where code=:code order by date")
                .setString("code", code).list();
        se.close();
        if (re == null || re.size() == 0)
            throw new ObjectNotFoundException("fundCode: " + code + " not found.");
        List<NetWorthEntity> entities = new ArrayList<>();
        for (Object obj : re) {
            entities.add((NetWorthEntity) obj);
        }
        return entities;
    }

    @Override
    public List<NetWorthEntity> getNetWorth(String code, String startDate, String endDate) throws ObjectNotFoundException {
        Session se = HibernateBoot.openSession();
        List re = se.createQuery("from NetWorthEntity where code=:code and date between " +
                ":startDate and :endDate order by date").setString("startDate", startDate)
                .setString("endDate", endDate).setString("code", code).list();
        se.close();
        if (re == null || re.size() == 0)
            throw new ObjectNotFoundException("fundCode: " + code + " not found.");
        List<NetWorthEntity> entities = new ArrayList<>();
        for (Object obj : re) {
            entities.add((NetWorthEntity) obj);
        }
        return entities;
    }
}
