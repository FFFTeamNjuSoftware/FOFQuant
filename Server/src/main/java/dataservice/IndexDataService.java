package dataservice;

import entities.IndexPriceEntity;
import exception.ObjectNotFoundException;

import java.util.List;

/**
 * Created by Daniel on 2016/8/18.
 */
public interface IndexDataService {
    List<IndexPriceEntity> getIndexPriceInfo(String code) throws ObjectNotFoundException;
}
