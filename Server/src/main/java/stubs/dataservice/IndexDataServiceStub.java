package stubs.dataservice;

import dataservice.IndexDataService;
import entities.IndexPriceEntity;
import exception.ObjectNotFoundException;

import java.util.List;

/**
 * Created by Daniel on 2016/8/18.
 */
public class IndexDataServiceStub implements IndexDataService {
    @Override
    public List<IndexPriceEntity> getIndexPriceInfo(String code) throws ObjectNotFoundException {
        return null;
    }
}
