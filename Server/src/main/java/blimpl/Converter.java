package blimpl;

import beans.FundInfo;
import beans.UserInfo;
import beans.UserManageInfo;
import entities.FundInfosEntity;
import entities.UserEntity;
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

}
