package blimpl;

import beans.*;
import entities.*;
import util.UserType;

/**
 * Created by Daniel on 2016/8/16.
 */
public class Converter {

    public static UserInfo convertUserInfo(UserEntity entity) {
        UserInfo info = new UserInfo();
        info.name = entity.getName();
        info.gender = entity.getGender();
        info.userType = UserType.getStringValue(entity.getUsertype());
        return info;
    }

    public static UserManageInfo convertUserManageInfo(UserEntity entity) {
        UserManageInfo info = new UserManageInfo();
        info.password = entity.getPassword();
        info.username = entity.getUsername();
        info.name = entity.getName();
        info.gender = entity.getGender();
        info.userType = UserType.getStringValue(entity.getUsertype());
        return info;
    }

    public static UserEntity convertUserEntity(UserManageInfo manageInfo) {
        UserEntity entity = new UserEntity();
        entity.setUsertype(manageInfo.userType.toString());
        entity.setUsername(manageInfo.username);
        entity.setPassword(manageInfo.password);
        entity.setGender(manageInfo.gender);
        entity.setName(manageInfo.name);
        return entity;
    }

    public static FundInfo convertFundInfo(FundInfosEntity entity) {
        FundInfo info = new FundInfo();
        info.compare_base = entity.getCompareBase();
        info.establish_date = entity.getEstablishDate();
        info.establish_scale = entity.getEstablishScale();
        info.full_name = entity.getFullName();
        info.fund_type = entity.getFundType();
        info.invest_strategy = entity.getInvestStrategy();
        info.invest_target = entity.getInvestTarget();
        info.manage_fee = entity.getManageFee();
        info.risk_feature = entity.getRiskFeature();
        info.scale = entity.getScale();
        info.simple_name = entity.getSimpleName();
        info.invest_type = entity.getInvestType();
        info.rank = entity.getRank() == null ? 0 : entity.getRank();
        return info;
    }

    public static AssetAllocation convertAssetAllocation(AssetAllocationEntity entity) {
        AssetAllocation assetAllocation = new AssetAllocation();
        assetAllocation.date = entity.getDate();
        assetAllocation.bond_ratio = entity.getBondRatio();
        assetAllocation.bond_value = entity.getBondValue();
        assetAllocation.cash_ratio = entity.getCashRatio();
        assetAllocation.cash_value = entity.getCashValue();
        assetAllocation.net_value = entity.getNetValue();
        assetAllocation.other_ratio = entity.getOtherRatio();
        assetAllocation.other_value = entity.getOtherValue();
        assetAllocation.stock_ratio = entity.getStockRatio();
        assetAllocation.stock_value = entity.getStockValue();
        assetAllocation.total_value = entity.getTotalValue();
        return assetAllocation;
    }

    public static HoldingUnit convertHoldingUnit(StockHoldInfoEntity entity) {
        HoldingUnit unit = new HoldingUnit();
        unit.code = entity.getStockCode();
        unit.holdNum = entity.getHoldNum();
        unit.name = entity.getStockName();
        unit.value = entity.getValue();
        unit.ratio = entity.getRatio();
        return unit;
    }

    public static HoldingUnit convertHoldingUnit(IndustryHoldInfoEntity entity) {
        HoldingUnit unit = new HoldingUnit();
        unit.code = entity.getIndustryCode();
        unit.name = entity.getIndustryName();
        unit.value = entity.getValue();
        unit.ratio = entity.getRatio();
        return unit;
    }

    public static HoldingUnit convertHoldingUnit(BondHoldInfoEntity entity) {
        HoldingUnit unit = new HoldingUnit();
        unit.code = entity.getBondCode();
        unit.holdNum = entity.getHoldNum();
        unit.name = entity.getBondName();
        unit.value = entity.getValue();
        unit.ratio = entity.getRatio();
        return unit;
    }

    public static PriceInfo convertPriceInfo(NetWorthEntity entity) {
        PriceInfo info = new PriceInfo();
        info.date = entity.getDate().toString();
        info.price = entity.getUnitWorth();
        info.rise = entity.getDailyRise();
        info.total_netWorth = entity.getTotalWorth();
        return info;
    }

    public static PriceInfo convertPriceInfo(IndexPriceEntity entity) {
        PriceInfo info = new PriceInfo();
        info.date = entity.getDate();
        info.price = entity.getClose();
        info.rise = entity.getDaily_rise();
        return info;
    }

    public static FundQuickInfo convertFundQuickInfo(FundQuickInfosEntity entity) {
        FundQuickInfo info = new FundQuickInfo();
        info.code = entity.getFundCode();
        info.current_netWorth = entity.getNetWorth();
        info.daily_rise = entity.getDailyRise();
        info.simple_name = entity.getSimpleName();
        info.oneMonth = entity.getOneMonth();
        info.threeMonth = entity.getThreeMonth();
        info.halfYear = entity.getSixMonth();
        info.oneYear = entity.getOneYear();
        info.threeYear = entity.getThreeYear();
        info.fiveYear = entity.getFiveYear();
        info.sinceEstablish = entity.getSinceEstablish();
        info.yearRate = entity.getYearRate();
        return info;
    }

    public static ConstParameter convertConstParameter(ConstParameterEntity entity) {
        ConstParameter constParameter = new ConstParameter();
        constParameter.noRiskProfit = entity.getNoRiskProfit();
        return constParameter;
    }

    public static PositionChange convertPositionChange(PositionChangeEntity entity) {
        PositionChange positionChange = new PositionChange();
        positionChange.buyNum = entity.getBuyNum();
        positionChange.buyPrice = entity.getBuyPrice();
        positionChange.changeDate = entity.getChangeDate();
        positionChange.changeTime = entity.getChangeTime();
        positionChange.fundCode = entity.getFundCode();
        positionChange.saleNum = entity.getSaleNum();
        positionChange.fundName = entity.getFundName();
        positionChange.salePrice = entity.getSalePrice();
        return positionChange;
    }

    public static AssetItem convertFOFAssetAllocation(FofAssetAllocationEntity entity){
        AssetItem assetItem=new AssetItem();
        assetItem.code=entity.getFundCode();
        assetItem.name=entity.getFundName();
        assetItem.endHoldNum=entity.getEndHoldNum();
        assetItem.endHoldRatio=entity.getEndHoldRatio();
        assetItem.endHoldValue=entity.getEndHoldValue();
        assetItem.endNetWorth=entity.getEndNetWorth();
        assetItem.periodFloatProfit=entity.getPeriodFloatProfit();
        assetItem.periodProfit=entity.getPeriodProfit();
        assetItem.periodProfitRate=entity.getPeriodProfitRate();
        assetItem.profitRatio=entity.getProfitRatio();
        assetItem.unitProfit=entity.getUnitProfit();
        return assetItem;
    }
}
