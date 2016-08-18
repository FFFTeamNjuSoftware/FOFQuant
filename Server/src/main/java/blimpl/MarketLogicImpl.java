package blimpl;

import beans.PriceInfo;
import beans.ProfitChartInfo;
import beans.ProfitRateInfo;
import bl.MarketLogic;
import dataservice.MarketDataService;
import dataserviceimpl.DataServiceController;
import entities.NetWorthEntity;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import util.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * Created by Daniel on 2016/8/15.
 */
public class MarketLogicImpl extends UnicastRemoteObject implements MarketLogic {

    private static MarketLogic instance;
    private MarketDataService marketDataService;

    private MarketLogicImpl() throws RemoteException {
        marketDataService = DataServiceController.getMarketDataService();
    }

    public static MarketLogic getInstance() {
        if (instance == null)
            try {
                instance = new MarketLogicImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        return instance;
    }

    @Override
    public List<PriceInfo> getPriceInfo(String code, UnitType type) throws RemoteException, ObjectNotFoundException {
        List<NetWorthEntity> entities = marketDataService.getNetWorth(code);
        List<PriceInfo> infos = new ArrayList<>();
        DateConstraint constraint = DateConstrainGenerator.getDateConstrainByUnitType(type);
        double rise = 1;
        for (int i = 0; i < entities.size(); i++) {
            rise *= (1 + entities.get(i).getDailyRise() / 100);
            if (i == entities.size() - 1) {
                PriceInfo info = Converter.convertPriceInfo(entities.get(i));
                info.rise = (rise - 1) * 100;
                infos.add(info);
                break;
            }
            if (constraint.value(CalendarOperate.getCalendarByString(entities.get(i).getDate()
                    .toString()), CalendarOperate.getCalendarByString(entities.get(i + 1).getDate()
                    .toString()))) {
                PriceInfo info = Converter.convertPriceInfo(entities.get(i));
                info.rise = (rise - 1) * 100;
                infos.add(info);
                rise = 1;
            }
        }
        return infos;
    }

    @Override
    public List<PriceInfo> getPriceInfo(String code, UnitType type, int counts) throws RemoteException, ObjectNotFoundException, ParameterException {
        List<PriceInfo> infos = getPriceInfo(code, type);
        int size = infos.size();
        if (counts > size)
            throw new ParameterException("required counts:" + counts + " is larger than true info " +
                    "counts:" + size);
        return infos.subList(size - counts, size);
    }

    @Override
    public List<PriceInfo> getPriceInfo(String code, UnitType type, String startDate, String endDate) throws RemoteException, ObjectNotFoundException, ParameterException {
        if (startDate.compareTo(endDate) > 0)
            throw new ParameterException("starDate:" + startDate + " is behind endDate:" + endDate);
        List<PriceInfo> infos = getPriceInfo(code, type);
        Collections.sort(infos, (e1, e2) -> e1.date.compareTo(e2.date));
        int startIndex = 0, endIndex = infos.size() - 1;
        while (startDate.compareTo(infos.get(startIndex).date) > 0)
            startIndex++;
        while (endDate.compareTo(infos.get(endIndex).date) < 0)
            endIndex--;
        endIndex++;
        return infos.subList(startIndex, endIndex);
    }

    @Override
    public ProfitRateInfo getProfitRateInfo(String code)
            throws RemoteException, ObjectNotFoundException {
        List<PriceInfo> infos = getPriceInfo(code, UnitType.DAY);
        return getProfitRateInfoByPriceInfos(infos);
    }

    @Override
    public List<ProfitChartInfo> getFundProfitInfoChart(String code, UnitType type, TimeType
            timeType, ChartType chartType) throws RemoteException, ObjectNotFoundException {
        String[] dates = getDates(timeType);
        return null;
    }

    /**
     * 根据PriceInfo 获得收益率数据
     * 其中年化收益率按平均日收益率*252算
     *
     * @param infos
     * @return
     */
    private ProfitRateInfo getProfitRateInfoByPriceInfos(List<PriceInfo> infos) {
        Collections.sort(infos, (e1, e2) -> e1.date.compareTo(e2.date));
        ProfitRateInfo profitRateInfo = new ProfitRateInfo();
        double sinceEstablish = 1.0;
        double yearRate = 0.0;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        String oneMonth = CalendarOperate.formatCalender(calendar);
        calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -3);
        String threeMonth = CalendarOperate.formatCalender(calendar);
        calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -6);
        String sixMonth = CalendarOperate.formatCalender(calendar);
        calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        String oneYear = CalendarOperate.formatCalender(calendar);
        calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -3);
        String threeYear = CalendarOperate.formatCalender(calendar);
        calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -5);
        String fiveYear = CalendarOperate.formatCalender(calendar);
        Map<String, Double> dateProfitInfo = new HashMap<>();
        dateProfitInfo.put(oneMonth, 1.0);
        dateProfitInfo.put(threeMonth, 1.0);
        dateProfitInfo.put(sixMonth, 1.0);
        dateProfitInfo.put(oneYear, 1.0);
        dateProfitInfo.put(threeYear, 1.0);
        dateProfitInfo.put(fiveYear, 1.0);

        System.out.println(oneMonth);

        for (PriceInfo info : infos) {
            dateProfitInfo.forEach((key, value) -> {
                if (info.date.compareTo(key) > 0) {
//                    if (key.equals("2016-07-18"))
//                        System.out.println(value + "," + info.rise);
                    dateProfitInfo.put(key, value * (1 + info.rise / 100));
                }
            });

            yearRate += info.rise;
            sinceEstablish *= (1 + info.rise / 100);
        }
        sinceEstablish = sinceEstablish - 1;
        yearRate = yearRate / infos.size() * 252;
        profitRateInfo.nearFiveYear = (dateProfitInfo.get(fiveYear) - 1) * 100;
        profitRateInfo.nearOneMonth = (dateProfitInfo.get(oneMonth) - 1) * 100;
        profitRateInfo.nearThreeMonth = (dateProfitInfo.get(threeMonth) - 1) * 100;
        profitRateInfo.nearSixMonth = (dateProfitInfo.get(sixMonth) - 1) * 100;
        profitRateInfo.nearThreeYear = (dateProfitInfo.get(threeYear) - 1) * 100;
        profitRateInfo.nearFiveYear = (dateProfitInfo.get(fiveYear) - 1) * 100;
        profitRateInfo.yearRate = yearRate;
        profitRateInfo.sinceEstablish = sinceEstablish * 100;
        return profitRateInfo;
    }

    /**
     * 根据指定类型返回开始日期和结束日期
     *
     * @param timeType 时间类型
     * @return String数组，一次为开始日期和结束日期
     */
    private String[] getDates(TimeType timeType) {
        String[] re = new String[2];
        Calendar cal = Calendar.getInstance();
        re[0] = CalendarOperate.formatCalender(cal);
        switch (timeType) {
            case ONE_MONTH:
                cal.add(Calendar.MONTH, -1);
                break;
            case THREE_MONTH:
                cal.add(Calendar.MONTH, -3);
                break;
            case ONE_YEAR:
                cal.add(Calendar.YEAR, -1);
                break;
            case THREE_YEAR:
                cal.add(Calendar.YEAR, -3);
                break;
            case SINCE_ESTABLISH:
                cal.add(Calendar.YEAR, -100);
                break;
        }
        re[1] = CalendarOperate.formatCalender(cal);
        return re;
    }
}
