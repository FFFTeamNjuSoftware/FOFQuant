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
}
