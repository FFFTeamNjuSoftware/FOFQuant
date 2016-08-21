package stubs.dataservice;

import beans.CodeName;
import dataservice.BaseInfoDataService;
import entities.CompanyInfoEntity;
import entities.ConstParameterEntity;
import entities.FundInfosEntity;
import entities.FundQuickInfosEntity;
import exception.ObjectNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 2016/8/16.
 */
public class BaseInfoDataServiceStub implements BaseInfoDataService {
    @Override
    public FundInfosEntity getFundInfo(String code) throws ObjectNotFoundException {
        FundInfosEntity fundInfosEntity = new FundInfosEntity();
        fundInfosEntity.setCode("001547");
        fundInfosEntity.setCompareBase("兴业基金");
        fundInfosEntity.setEstablishDate("20150708");
        fundInfosEntity.setEstablishScale(20000.0);
        fundInfosEntity.setFullName("兴业聚惠灵活配置混合A");
        fundInfosEntity.setFundType("混合型");
        fundInfosEntity.setInvestStrategy("低风险");
        fundInfosEntity.setInvestTarget("保值");
        fundInfosEntity.setInvestType("混合类");
        fundInfosEntity.setManageFee(0.01);
        fundInfosEntity.setNetValue(0.01);
        fundInfosEntity.setRiskFeature("高");
        fundInfosEntity.setScale(0.59);
        fundInfosEntity.setSimpleName("兴业聚惠A");
        return fundInfosEntity;
    }

    @Override
    public List<CodeName> fuzzySearch(String keyword) {
        List<CodeName> codeNameList = new ArrayList<CodeName>();
        CodeName codeName = new CodeName();
        codeName.code = "001547";
        codeName.name = "华夏聚惠A";
        codeNameList.add(codeName);
        return codeNameList;
    }

    @Override
    public List<String> getAllCodes() {
        return null;
    }

    @Override
    public List<String> getSectorCodes(String sectorId) {
        return null;
    }

    @Override
    public FundQuickInfosEntity getFundQuickInfo(String code) throws ObjectNotFoundException {
        return null;
    }

    @Override
    public CompanyInfoEntity getCompanyInfo(String company_id) throws ObjectNotFoundException {
        return null;
    }

    @Override
    public List<String> getCompanyFundCodes(String company_id) throws ObjectNotFoundException {
        return null;
    }

    @Override
    public List<CompanyInfoEntity> getAllCompanyInfos() {
        return null;
    }

    @Override
    public ConstParameterEntity getConstParameter() {
        return null;
    }
}
