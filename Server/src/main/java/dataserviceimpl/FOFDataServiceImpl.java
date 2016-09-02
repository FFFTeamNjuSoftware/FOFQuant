package dataserviceimpl;

import dataservice.FOFDataService;
import entities.FofAssetAllocationEntity;
import entities.FofHoldInfoEntity;
import entities.FofInfoEntity;
import entities.PositionChangeEntity;
import exception.ObjectNotFoundException;
import org.hibernate.Session;
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
    public List<PositionChangeEntity> getPositionChange(String fof_code) throws ObjectNotFoundException {
        Session se = HibernateBoot.openSession();
        List<?> li = se.createQuery("from PositionChangeEntity where fofCode=:fofCode").setString
                ("fofCode", fof_code).list();
        se.close();
        if (li == null || li.size() == 0)
            throw new ObjectNotFoundException("can't find " + PositionChangeEntity.class
                    .getSimpleName() + ":" + fof_code);
        return li.stream().map(e -> (PositionChangeEntity) e).collect(Collectors.toList());
    }
}
