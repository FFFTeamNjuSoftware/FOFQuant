package dataserviceimpl;

import dataservice.FOFDataService;
import entities.*;
import exception.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import startup.HibernateBoot;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by st0001 on 2016/9/1.
 */
public class FOFDataServiceImpl implements FOFDataService {

    protected FOFDataServiceImpl() {

    }


    @Override
    public List<FofAssetAllocationEntity> getFOFAssetAllocation(String fof_code) throws ObjectNotFoundException {
        Session se = HibernateBoot.openSession();
        List<?> li = se.createQuery("from FofAssetAllocationEntity where fofId=:fofCode")
                .setString
                        ("fofCode", fof_code).list();
        se.close();
        if (li == null || li.size() == 0)
            throw new ObjectNotFoundException("can't find " + FofAssetAllocationEntity.class
                    .getSimpleName() + ":" + fof_code);
        return li.stream().map(e -> (FofAssetAllocationEntity) e).collect(Collectors.toList());
    }

    @Override
    public FofInfoEntity getFofInfoEntity(String fof_code) throws ObjectNotFoundException {
        Session se = HibernateBoot.openSession();
        List<?> li = se.createQuery("from FofInfoEntity where fofId=:fofCode")
                .setString
                        ("fofCode", fof_code).list();
        se.close();
        if (li == null || li.size() == 0)
            throw new ObjectNotFoundException("can't find " + FofAssetAllocationEntity.class
                    .getSimpleName() + ":" + fof_code);
        return (FofInfoEntity) li.get(0);
    }

    @Override
    public List<FofHoldInfoEntity> getFofHoldInfos(String fof_code) throws ObjectNotFoundException {
        Session se = HibernateBoot.openSession();
        List<?> li = se.createQuery("from FofHoldInfoEntity where fofId=:fofCode order by date")
                .setString("fofCode", fof_code).list();
        se.close();
        if (li == null || li.size() == 0)
            throw new ObjectNotFoundException("can't find " + FofHoldInfoEntity.class
                    .getSimpleName() + ":" + fof_code);
        return li.stream().map(e -> (FofHoldInfoEntity) e).collect(Collectors.toList());
    }

    @Override
    public List<FofHoldInfoEntity> getFofHoldInfos(String fof_code, String startDate, String endDate) throws ObjectNotFoundException {
        Session se = HibernateBoot.openSession();
        List<?> li = se.createQuery("from FofHoldInfoEntity where fofId=:fofCode and date between" +
                " :startDate And :endDate order by date").setString("startDate", startDate)
                .setString("endDate", endDate).setString("fofCode", fof_code).list();
        se.close();
        if (li == null || li.size() == 0)
            throw new ObjectNotFoundException("can't find " + FofHoldInfoEntity.class
                    .getSimpleName() + ":" + fof_code);
        return li.stream().map(e -> (FofHoldInfoEntity) e).collect(Collectors.toList());
    }

    @Override
    public List<FofHoldInfoEntity> getNewestFofHoldInfos(String fof_code) throws ObjectNotFoundException {
        Session se = HibernateBoot.openSession();
        List<?> li = se.createQuery("from FofHoldInfoEntity where fofId=:fofCode and date in " +
                "(select max(date) from FofHoldInfoEntity )").setString("fofCode", fof_code).list();
        se.close();
        if (li == null || li.size() == 0)
            throw new ObjectNotFoundException("can't find " + FofHoldInfoEntity.class
                    .getSimpleName() + ":" + fof_code);
        return li.stream().map(e -> (FofHoldInfoEntity) e).collect(Collectors.toList());
    }

    @Override
    public List<PositionChangeEntity> getPositionChange(String fof_code) throws ObjectNotFoundException {
        Session se = HibernateBoot.openSession();
        List<?> li = se.createQuery("from PositionChangeEntity where fofCode=:fofCode and isHandle!=0")
                .setString("fofCode", fof_code).list();
        se.close();
        if (li == null || li.size() == 0)
            throw new ObjectNotFoundException("can't find " + PositionChangeEntity.class
                    .getSimpleName() + ":" + fof_code);
        return li.stream().map(e -> (PositionChangeEntity) e).collect(Collectors.toList());
    }


    @Override
    public List<PositionChangeEntity> getPositionChangeRequest(String fof_code) throws
            ObjectNotFoundException {
        Session se = HibernateBoot.openSession();
        List<?> li = se.createQuery("from PositionChangeEntity where fofCode=:fofCode and isHandle=0")
                .setString("fofCode", fof_code).list();
        se.close();
        if (li == null || li.size() == 0)
            throw new ObjectNotFoundException("can't find " + PositionChangeEntity.class
                    .getSimpleName() + ":" + fof_code);
        return li.stream().map(e -> (PositionChangeEntity) e).collect(Collectors.toList());
    }


    @Override
    public List<FofHistoryInfoEntity> getFofHistoryInfos(String fof_code) throws ObjectNotFoundException {
        Session se = HibernateBoot.openSession();
        List<?> li = se.createQuery("from FofHistoryInfoEntity where fofId=:fofCode order by " +
                "date").setString("fofCode", fof_code).list();
        se.close();
        if (li == null || li.size() == 0)
            throw new ObjectNotFoundException("can't find " + FofHistoryInfoEntity.class
                    .getSimpleName() + ":" + fof_code);
        return li.stream().map(e -> (FofHistoryInfoEntity) e).collect(Collectors.toList());
    }

    @Override
    public FofHistoryInfoEntity getNewestHistoryInfo(String fof_code) throws ObjectNotFoundException {
        Session se = HibernateBoot.openSession();
        List<?> li = se.createQuery("from FofHistoryInfoEntity where fofId=:fofCode and date in " +
                "(select max(date) from FofHistoryInfoEntity )").setString("fofCode", fof_code).list();
        se.close();
        if (li == null || li.size() == 0)
            throw new ObjectNotFoundException("can't find " + FofHoldInfoEntity.class
                    .getSimpleName() + ":" + fof_code);
        return (FofHistoryInfoEntity) li.get(0);
    }

    @Override
    public List<FofEstablishInfoEntity> getFofEstablishInfo(String fof_code) throws ObjectNotFoundException {
        Session se = HibernateBoot.openSession();
        List<?> li = se.createQuery("from FofEstablishInfoEntity where fofCode=:fofCode").setString("fofCode",
                fof_code).list();
        se.close();
        if (li == null || li.size() == 0)
            throw new ObjectNotFoundException("can't find " + FofHoldInfoEntity.class
                    .getSimpleName() + ":" + fof_code);
        return li.stream().map(e -> (FofEstablishInfoEntity) e).collect(Collectors.toList());
    }

    @Override
    public void savePositionChangeEntity(List<PositionChangeEntity> entities) throws ObjectNotFoundException {
        Session se = HibernateBoot.openSession();
        Transaction tra = se.beginTransaction();
        entities.forEach(e -> se.saveOrUpdate(e));
        tra.commit();
        se.close();
    }
}
