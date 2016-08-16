package stubs.dataservice;

import beans.CodeName;
import dataservice.BaseInfoDataService;
import entities.FundInfosEntity;
import exception.ObjectNotFoundException;

import java.util.List;

/**
 * Created by Daniel on 2016/8/16.
 */
public class BaseInfoDataServiceStub implements BaseInfoDataService {
    @Override
    public FundInfosEntity getFundInfo(String code) throws ObjectNotFoundException {
        return null;
    }

    @Override
    public List<CodeName> fuzzySearch(String keyword) {
        return null;
    }
}
