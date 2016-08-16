package dataserviceimpl;

import beans.CodeName;
import dataservice.BaseInfoDataService;
import entities.FundInfosEntity;
import exception.ObjectNotFoundException;
import org.hibernate.Session;
import startup.HibernateBoot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 2016/8/16.
 */
public class BaseInfoDataServiceImpl implements BaseInfoDataService {

    protected BaseInfoDataServiceImpl() {
    }

    @Override
    public FundInfosEntity getFundInfo(String code) throws ObjectNotFoundException {
        Session se = HibernateBoot.openSession();
        List re = se.createQuery("from FundInfosEntity where code=:code").setString("code", code)
                .list();
        se.close();
        if (re == null || re.size() == 0)
            throw new ObjectNotFoundException("can't find this fund");
        return (FundInfosEntity) re.get(0);
    }

    @Override
    public List<CodeName> fuzzySearch(String keyword) {
        Session se = HibernateBoot.openSession();
        List re = se.createQuery("from FundInfosEntity where code like :keyword or simpleName like :keyword")
                .setString("keyword", "%" + keyword + "%").list();
        se.close();
        List<CodeName> codeNames = new ArrayList<>();
        for (Object obj : re) {
            CodeName item = new CodeName();
            FundInfosEntity entity = (FundInfosEntity) obj;
            item.code = entity.getCode();
            item.name = entity.getSimpleName();
            codeNames.add(item);
        }
        return codeNames;
    }
}
