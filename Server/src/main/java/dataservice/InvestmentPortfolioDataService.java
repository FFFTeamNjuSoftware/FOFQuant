package dataservice;

import entities.AssetAllocationEntity;
import entities.BondHoldInfoEntity;
import entities.IndustryHoldInfoEntity;
import entities.StockHoldInfoEntity;
import exception.ObjectNotFoundException;

import java.util.List;

/**
 * Created by Daniel on 2016/8/16.
 */
public interface InvestmentPortfolioDataService {
    /**
     * 获得重仓债券
     *
     * @param code
     * @return
     */
    public List<BondHoldInfoEntity> getBondHoldInfo(String code) throws ObjectNotFoundException;

    /**
     * 获得重仓股票
     *
     * @param code
     * @return
     */
    public List<StockHoldInfoEntity> getStockHoldInfo(String code) throws ObjectNotFoundException;

    ;

    /**
     * 获得重仓行业
     *
     * @param code
     * @return
     */
    public List<IndustryHoldInfoEntity> getIndustryHold(String code) throws ObjectNotFoundException;

    ;

    /**
     * 获得大类资产配置情况
     *
     * @param code
     * @return
     */
    public List<AssetAllocationEntity> getAssetAllocations(String code) throws ObjectNotFoundException;

    ;

}
