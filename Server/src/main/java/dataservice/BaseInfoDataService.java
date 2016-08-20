package dataservice;

import beans.CodeName;
import entities.FundInfosEntity;
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
     * 获得一个版块内的基金代码
     * @param sectorId
     * @return
     */
    public List<String> getSectorCodes(String sectorId) throws ObjectNotFoundException;

}
