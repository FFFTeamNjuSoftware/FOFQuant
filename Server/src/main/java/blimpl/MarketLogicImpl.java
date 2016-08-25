package blimpl;

import beans.PriceInfo;
import beans.ProfitChartInfo;
import beans.ProfitRateInfo;
import bl.MarketLogic;
import dataservice.IndexDataService;
import dataservice.MarketDataService;
import dataserviceimpl.DataServiceController;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import util.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Daniel on 2016/8/15.
 */
public class MarketLogicImpl extends UnicastRemoteObject implements MarketLogic {

    private static MarketLogic instance;
    private MarketDataService marketDataService;
    private IndexDataService indexDataService;

    private MarketLogicImpl() throws RemoteException {
        marketDataService = DataServiceController.getMarketDataService();
        indexDataService = DataServiceController.getIndexDataService();
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
        List<PriceInfo> tems = null;
        if (code.charAt(0) != 'I') {
            tems = marketDataService.getNetWorth(code).stream().map(Converter::convertPriceInfo).collect
                    (Collectors.toList());
        } else {
            tems = indexDataService.getIndexPriceInfo(code.substring(1)).stream().map
                    (Converter::convertPriceInfo).collect(Collectors.toList());
        }
        List<PriceInfo> infos = new ArrayList<>();
        DateConstraint constraint = DateConstrainGenerator.getDateConstrainByUnitType(type);
        double rise = 1;
        for (int i = 0; i < tems.size(); i++) {
            rise *= (1 + tems.get(i).rise / 100);
            if (i == tems.size() - 1) {
                PriceInfo info = tems.get(i);
                info.rise = (rise - 1) * 100;
                NumberOpe.controlDecimal(info, 2);
                infos.add(info);
                break;
            }
            if (constraint.value(CalendarOperate.getCalendarByString(tems.get(i).date), CalendarOperate
                    .getCalendarByString(tems.get(i + 1).date))) {
                PriceInfo info = tems.get(i);
                info.rise = (rise - 1) * 100;
                NumberOpe.controlDecimal(info, 2);
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
        List<PriceInfo> re = new ArrayList<>();
        re.addAll(infos.subList(size - counts, size));
        return re;
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
        List<ProfitChartInfo> chartInfos = new ArrayList<>();
        try {
            List<PriceInfo> fundInfos = getPriceInfo(code, type, dates[0], dates[1]);
            List<PriceInfo> fundIndex = getPriceInfo("I000011", type, dates[0], dates[1]);
            List<PriceInfo> szIndex = getPriceInfo("I000001", type, dates[0], dates[1]);
            fundInfos.get(0).rise = 0;
            fundIndex.get(0).rise = 0;
            szIndex.get(0).rise = 0;
            int i = 0;
            while (fundIndex.get(i).date.compareTo(fundInfos.get(0).date) < 0)
                i++;
            int k = 0;
            while (szIndex.get(k).date.compareTo(fundInfos.get(0).date) < 0)
                k++;
            double fund_rise = 1, fundIndex_rise = 1, szIndex_rise = 1;
            for (int j = 0; j < fundInfos.size(); j++) {
                ProfitChartInfo chartInfo = new ProfitChartInfo();
                chartInfo.date = fundInfos.get(j).date;
                chartInfo.values = new double[3];
                fund_rise *= (1 + fundInfos.get(j).rise / 100);
                chartInfo.values[0] = fund_rise;
                while (i < fundIndex.size() && fundIndex.get(i).date.compareTo(fundInfos.get(j).date)
                        <= 0) {
                    fundIndex_rise *= (1 + fundIndex.get(i).rise / 100);
                    i++;
                }
                chartInfo.values[1] = fundIndex_rise;
                while (k < szIndex.size() && szIndex.get(k).date.compareTo(fundInfos.get(j).date)
                        <= 0) {
                    szIndex_rise *= (1 + szIndex.get(k).rise / 100);
                    k++;
                }
                chartInfo.values[2] = szIndex_rise;
                chartInfos.add(chartInfo);
            }
            for (ProfitChartInfo chartInfo : chartInfos) {
                for (int d = 0; d < 3; d++) {
                    if (chartType == ChartType.MILLION_WAVE_CHART) {
                        chartInfo.values[d] *= 10000;
                    } else {
                        chartInfo.values[d] = (chartInfo.values[d] - 1) * 100;
                    }
                    NumberOpe.controlDecimal(chartInfo, 2);
                }
            }
        } catch (ParameterException e) {
            e.printStackTrace();
        }
        return chartInfos;
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


        for (PriceInfo info : infos) {
            dateProfitInfo.forEach((key, value) -> {
                if (info.date.compareTo(key) > 0) {
                    dateProfitInfo.put(key, value * (1 + info.rise / 100));
                }
            });
            yearRate += info.rise;
            sinceEstablish *= (1 + info.rise / 100);
        }
        sinceEstablish = sinceEstablish - 1;
        System.out.println(yearRate + "," + infos.size());
        if (infos.size() > 252)
            yearRate = yearRate / infos.size() * 252;
        else
            yearRate = 0;
        profitRateInfo.nearOneMonth = (dateProfitInfo.get(oneMonth) - 1) * 100;
        profitRateInfo.nearThreeMonth = (dateProfitInfo.get(threeMonth) - 1) * 100;
        profitRateInfo.nearSixMonth = (dateProfitInfo.get(sixMonth) - 1) * 100;
        profitRateInfo.nearOneYear = (dateProfitInfo.get(oneYear) - 1) * 100;
        profitRateInfo.nearThreeYear = (dateProfitInfo.get(threeYear) - 1) * 100;
        profitRateInfo.nearFiveYear = (dateProfitInfo.get(fiveYear) - 1) * 100;
        profitRateInfo.yearRate = yearRate;
        profitRateInfo.sinceEstablish = sinceEstablish * 100;
        NumberOpe.controlDecimal(profitRateInfo, 2);
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
        re[1] = CalendarOperate.formatCalender(cal);
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
        re[0] = CalendarOperate.formatCalender(cal);
        return re;
    }
}
