package dataservice;

import beans.CodeName;
import beans.ConstParameter;
import entities.*;
import exception.ObjectNotFoundException;

import java.util.List;

/**
 * Created by Daniel on 2016/8/16.
 */
public interface BaseInfoDataService {
    /**
     * 获得基金基本信息
     *
     * @param code
     * @return
     */
    public FundInfosEntity getFundInfo(String code) throws ObjectNotFoundException;


    /**
     * 获得基金的评级信息
     * @param code
     * @return
     * @throws ObjectNotFoundException
     */
    public FundRankEntity getFundRankInfo(String code) throws ObjectNotFoundException;

    /**
     * 根据关键词模糊查找
     *
     * @param keyword
     * @return
     */
    public List<CodeName> fuzzySearch(String keyword);

    /**
     * 获得所有的基金代码
     *
     * @return
     */
    public List<String> getAllCodes();

    /**
     * 返回数据库中基金数据的最新的日期
     * @param code
     * @return
     */
    public String getMaxDate(String code);

    /**
     * 获得一个版块内的基金代码
     *
     * @param sectorId
     * @return
     */
    public List<String> getSectorCodes(String sectorId) throws ObjectNotFoundException;

    /**
     * 获得基金的快速信息
     *
     * @param code
     * @return
     * @throws ObjectNotFoundException
     */
    public FundQuickInfosEntity getFundQuickInfo(String code) throws ObjectNotFoundException;

    /**
     * 获得公司信息
     *
     * @param company_id
     * @return
     * @throws ObjectNotFoundException
     */
    public CompanyInfoEntity getCompanyInfo(String company_id) throws ObjectNotFoundException;

    /**
     * 获得所有的公司代码
     * @return
     */
    public List<CompanyInfoEntity> getAllCompanyInfos();

    /**
     * 获得公司旗下的基金代码
     *
     * @param company_id
     * @return
     * @throws ObjectNotFoundException
     */
    public List<String> getCompanyFundCodes(String company_id) throws ObjectNotFoundException;

    /**
     * 获得一些常量字段
     * @return
     */
    public ConstParameterEntity getConstParameter();

    /**
     * 更新常量字段
     * @param constParameter
     */
    void updateConstParameter (ConstParameter constParameter);
}
