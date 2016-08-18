package dataserviceimpl;

import dataservice.IndexDataService;
import entities.IndexPriceEntity;
import exception.ObjectNotFoundException;
import org.hibernate.Session;
import startup.HibernateBoot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 2016/8/18.
 */
public class IndexDataServiceImpl implements IndexDataService {
    protected IndexDataServiceImpl() {
    }

    @Override
    public List<IndexPriceEntity> getIndexPriceInfo(String code) throws ObjectNotFoundException {
        Session se = HibernateBoot.openSession();
        List<Object> re = se.createQuery("from IndexPriceEntity where code=:code order by date")
                .setString("code", code).list();
        se.close();
        if (re == null || re.size() == 0)
            throw new ObjectNotFoundException("can't find the index:" + code);
        List<IndexPriceEntity> entities = new ArrayList<>();
        re.stream().forEach(e -> entities.add((IndexPriceEntity) e));
        return entities;
    }
}
