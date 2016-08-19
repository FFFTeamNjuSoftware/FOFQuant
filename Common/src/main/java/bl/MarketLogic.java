package bl;

/**
 * Created by Daniel on 2016/8/15.
 */

import beans.PriceInfo;
import beans.ProfitChartInfo;
import beans.ProfitRateInfo;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import util.ChartType;
import util.TimeType;
import util.UnitType;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * 获得行情信息
 */
public interface MarketLogic extends Remote {
    /**
     * 代码默认为基金代码，如果需要获得指数数据，在基金最开始加上字符I
     */

    /**
     * 返回对应基金代码、指定周期类型的所有数据
     *
     * @param code 基金代码
     * @param type 周期类型
     * @return
     * @throws RemoteException
     * @throws ObjectNotFoundException
     * @throws ParameterException
     */
    public List<PriceInfo> getPriceInfo(String code, UnitType type) throws RemoteException,
            ObjectNotFoundException;

    /**
     * @param code
     * @param type
     * @param counts 返回的数据的条数（自当前日期算）
     * @return
     * @throws RemoteException
     * @throws ObjectNotFoundException
     * @throws ParameterException
     */
    public List<PriceInfo> getPriceInfo(String code, UnitType type, int counts) throws
            RemoteException, ObjectNotFoundException, ParameterException;

    /**
     * @param code
     * @param type
     * @param startDate 起始日期
     * @param endDate   结束日期
     * @return
     * @throws RemoteException
     * @throws ObjectNotFoundException
     * @throws ParameterException
     */
    public List<PriceInfo> getPriceInfo(String code, UnitType type, String startDate, String endDate)
            throws RemoteException, ObjectNotFoundException, ParameterException;

    /**
     * 获得基金的收益率指标
     *
     * @param code
     * @return
     */
    public ProfitRateInfo getProfitRateInfo(String code) throws RemoteException, ObjectNotFoundException;

    /**
     * 获得基金页面的图表的值
     * 返回的ProfitCharInfo 里的value为大小为3个数组，依次为基金、基金指数、沪深300指数的值
     *
     * @param code      基金代码
     * @param type      周期类型
     * @param timeType  时间类型
     * @param chartType 图表类型,分为万元波动图和折价/溢价图
     * @return
     * @throws RemoteException
     * @throws ObjectNotFoundException
     */
    public List<ProfitChartInfo> getFundProfitInfoChart(String code, UnitType type, TimeType
            timeType, ChartType chartType) throws RemoteException, ObjectNotFoundException;


}

