package dataserviceimpl;

import dataservice.InvestmentPortfolioDataService;
import entities.AssetAllocationEntity;
import entities.BondHoldInfoEntity;
import entities.IndustryHoldInfoEntity;
import entities.StockHoldInfoEntity;
import exception.ObjectNotFoundException;
import org.hibernate.Session;
import startup.HibernateBoot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 2016/8/16.
 */
public class InvestmentPortfolioDataServiceImpl implements InvestmentPortfolioDataService {

    protected InvestmentPortfolioDataServiceImpl() {
    }


    @Override
    public List<BondHoldInfoEntity> getBondHoldInfo(String code) throws ObjectNotFoundException {
        Session se = HibernateBoot.openSession();
        List re = se.createQuery("from BondHoldInfoEntity where fundCode=:code order by date desc,ratio desc")
                .setString("code", code).list();
        se.close();
        if (re == null || re.size() == 0)
            throw new ObjectNotFoundException("can't find the fund");
        List<BondHoldInfoEntity> bondHoldInfoEntities = new ArrayList<>();
        for (Object obj : re) {
            bondHoldInfoEntities.add((BondHoldInfoEntity) obj);
        }
        return bondHoldInfoEntities;
    }

    @Override
    public List<StockHoldInfoEntity> getStockHoldInfo(String code) throws ObjectNotFoundException {
        Session se = HibernateBoot.openSession();
        List re = se.createQuery("from StockHoldInfoEntity where fundCode=:code order by date desc ,ratio desc")
                .setString("code", code).list();
        se.close();
        if (re == null || re.size() == 0)
            throw new ObjectNotFoundException("can't find the fund");
        List<StockHoldInfoEntity> stockHoldInfoEntities = new ArrayList<>();
        for (Object obj : re) {
            stockHoldInfoEntities.add((StockHoldInfoEntity) obj);
        }
        return stockHoldInfoEntities;
    }

    @Override
    public List<IndustryHoldInfoEntity> getIndustryHold(String code) throws ObjectNotFoundException {
        Session se = HibernateBoot.openSession();
        List re = se.createQuery("from IndustryHoldInfoEntity where fundCode=:code order by date desc,ratio desc")
                .setString("code", code).list();
        se.close();
        if (re == null || re.size() == 0)
            throw new ObjectNotFoundException("can't find the fund");
        List<IndustryHoldInfoEntity> industryHoldInfoEntities = new ArrayList<>();
        for (Object obj : re) {
            industryHoldInfoEntities.add((IndustryHoldInfoEntity) obj);
        }
        return industryHoldInfoEntities;
    }

    @Override
    public List<AssetAllocationEntity> getAssetAllocations(String code) throws ObjectNotFoundException {
        Session se = HibernateBoot.openSession();
        List re = se.createQuery("from AssetAllocationEntity where code=:code  order by date desc ")
                .setString("code", code).list();
        se.close();
        if (re == null || re.size() == 0)
            throw new ObjectNotFoundException("can't find the fund");
        List<AssetAllocationEntity> assetAllocationEntities = new ArrayList<>();
        for (Object obj : re) {
            assetAllocationEntities.add((AssetAllocationEntity) obj);
        }
        return assetAllocationEntities;
    }
}
