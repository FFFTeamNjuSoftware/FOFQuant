package blimpl;

import beans.PriceInfo;
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
    public static void alignList(final List<PriceInfo> base, List<PriceInfo> target) {
        int k = 0;
        while (target.get(k).date.compareTo(base.get(0).date) < 0)
            k++;
        double rise = 1;
        for (int i = 0; i < base.size(); i++) {
            while (k < target.size() && target.get(k).date.compareTo(base.get(i).date) < 0) {
                if (k != target.size() - 1)
                    target.get(k + 1).rise = target.get(k).rise * (1 + target.get(k + 1).rise);
                target.remove(k);
            }
        }
    }
}
