package blimpl;

import beans.PriceInfo;
import bl.MarketLogic;
import util.CalendarOperate;
import util.TimeType;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Daniel on 2016/8/31.
 */
public class LogicUtil {
    /**
     * 根据指定类型返回开始日期和结束日期
     *
     * @param timeType 时间类型
     * @return String数组，一次为开始日期和结束日期
     */
    public static String[] getDates(TimeType timeType) {
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

    /**
     * 将target向base对其
     * 运行后两个List的长度应该是一样的
     * 其中target在某个日期缺失的信息由base中当天的信息代替
     *
     * @param base
     * @param target
     */
    public static void alignList(final List<PriceInfo> base, final List<PriceInfo> target) {
//        while (target.get(0).date.compareTo(base.get(0).date) < 0)
//            target.remove(0);
//        while (target.get(0).date.compareTo(base.get(0).date) > 0) {
//        }
//        for (int i = 0; i < base.size(); i++) {
//            if (i >= target.size()) {
//                PriceInfo priceInfo = new PriceInfo();
//                priceInfo.date = base.get(i).date;
//                priceInfo.rise = base.get(i).rise;
//                priceInfo.total_netWorth = base.get(i).total_netWorth;
//                priceInfo.price = base.get(i).price;
//                target.add(priceInfo);
//                continue;
//            }
//            while (k < target.size() && target.get(k).date.compareTo(base.get(i).date) < 0) {
//                if (k != target.size() - 1)
//                    target.get(k + 1).rise = target.get(k).rise * (1 + target.get(k + 1).rise / 100);
//                target.remove(k);
//            }
//            k++;
//        }
    }

    public static void main(String[] argss) {
        MarketLogic marketLogic = BLController.getMarketLogic();

//        List<PriceInfo> priceInfos=marketLogic.getPriceInfo("000001","")
    }

}
