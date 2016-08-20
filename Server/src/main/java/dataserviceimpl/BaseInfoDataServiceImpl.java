package dataserviceimpl;

import beans.CodeName;
import dataservice.BaseInfoDataService;
import entities.FundInfosEntity;
import entities.FundQuickInfosEntity;
import exception.ObjectNotFoundException;
import org.hibernate.Session;
import startup.HibernateBoot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<String> getAllCodes() {
        Session se = HibernateBoot.openSession();
        List<? extends Object> li = se.createQuery("select code from FundInfosEntity group by code")
                .list();
        se.close();
        List<String> re = new ArrayList<>();
        if (li == null)
            return re;
        return li.stream().map(e -> (String) e).collect(Collectors.toList());
    }

    @Override
    public List<String> getSectorCodes(String sectorId) throws ObjectNotFoundException {
        Session se = HibernateBoot.openSession();
        List<? extends Object> li = se.createQuery("select code from SectorFundInfoEntity where " +
                "sectorId=:sectorId").setString("sectorId", sectorId).list();
        se.close();
        if (li == null || li.size() == 0)
            throw new ObjectNotFoundException("sectorId:" + sectorId + " not found");
        return li.stream().map(e -> (String) e).collect(Collectors.toList());
    }

    @Override
    public FundQuickInfosEntity getFundQuickInfo(String code) throws ObjectNotFoundException {
        Session se = HibernateBoot.openSession();
        List re = se.createQuery("from FundQuickInfosEntity where fundCode=:code").setString("code", code)
                .list();
        se.close();
        if (re == null || re.size() == 0)
            throw new ObjectNotFoundException("can't find this fund:" + code);
        return (FundQuickInfosEntity) re.get(0);
    }
}
