package blimpl;

import beans.PriceInfo;
import bl.MarketLogic;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import util.TimeType;
import util.UnitType;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Daniel on 2016/8/31.
 */

/**
 * 用于帮助提取需要计算的数据，从数据层拿取出去并进行简单处理
 * 专用处理净值方面的数据
 */
public class CalculateDataHandler {
    /**
     * 基金代码
     */
    private String code;
    /**
     * 基准代码，可能有多个，默认为沪深300
     */
    private String[] baseCode;
    /**
     * 单位类型，默认为UnitType.DAY
     */
    private UnitType unitType;
    /**
     * 日期类型，默认为TimeType.YEAR
     */
    private TimeType timeType;
    /**
     * 开始日期、结束日期，可以不指定
     */
    private String startDate, endDate;

    private MarketLogic marketLogic;

    public CalculateDataHandler(String code) {
        this.code = code;
        baseCode = new String[]{"I000300"};
        unitType = UnitType.DAY;
        timeType = TimeType.ONE_YEAR;
        startDate = endDate = null;
        marketLogic = BLController.getMarketLogic();
    }

    /**
     * 设定基准代码
     *
     * @param baseCode
     * @return
     */
    public CalculateDataHandler setBaseCode(String... baseCode) {
        this.baseCode = baseCode;
        return this;
    }

    /**
     * 设定开始日期和结束日期
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public CalculateDataHandler setDate(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        return this;
    }

    /**
     * 设定开始日期，默认结束日期到现在
     *
     * @param startDate
     * @return
     */
    public CalculateDataHandler setStartDateWithoutEndDate(String startDate) {
        setDate(startDate, LocalDate.now().toString());
        return this;
    }

    /**
     * 获得数据，以PriceInfo的形式返回
     *
     * @return
     */
    public Map<String, List<PriceInfo>> getDatasByPriceInfo() throws ObjectNotFoundException {
        List<PriceInfo> priceInfos;
        if (startDate == null || endDate == null) {
            String[] dates = LogicUtil.getDates(timeType);
            startDate = dates[0];
            endDate = dates[1];
        }
        try {
            Map<String, List<PriceInfo>> result = new HashMap<>();
            priceInfos = marketLogic.getPriceInfo(code, unitType, startDate, endDate);
            result.put(code, priceInfos);
            for (String base : baseCode) {
                List<PriceInfo> baseInfo = marketLogic.getPriceInfo(base, unitType, startDate, endDate);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ParameterException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得涨幅，以PriceInfo的形式返回
     *
     * @return
     */
    public Map<String, List<Double>> getDatasByRise() throws ObjectNotFoundException {
        Map<String, List<PriceInfo>> priceInfos = getDatasByPriceInfo();
        Map<String, List<Double>> result = new HashMap<>();
        for (String key : priceInfos.keySet()) {
            List<PriceInfo> priceInfo = priceInfos.get(key);
            result.put(key, priceInfo.stream().map(e -> e.rise).collect(Collectors.toList()));
        }
        return result;
    }
}
