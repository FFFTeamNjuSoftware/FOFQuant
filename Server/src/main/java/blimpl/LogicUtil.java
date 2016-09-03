package blimpl;

import beans.PriceInfo;
import entities.FofEstablishInfoEntity;
import entities.FofHoldInfoEntity;
import entities.NetWorthEntity;
import exception.ObjectNotFoundException;
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
     * 其中target在某个日期缺失的信息由这段时间最邻近的值代替
     *
     * @param base
     * @param target
     */
    public static void alignList(final List<PriceInfo> base, final List<PriceInfo> target) {
        while (target.get(0).date.compareTo(base.get(0).date) < 0)
            target.remove(0);
        int t = 0;
        while (target.get(0).date.compareTo(base.get(t).date) > 0) {
            t++;
        }
        target.addAll(0, base.subList(0, t));
        for (int i = 0; i < base.size(); i++) {
            if (i >= target.size()) {
                PriceInfo priceInfo = target.get(target.size() - 1).copy();
                priceInfo.date = base.get(i).date;
                target.add(priceInfo);
                continue;
            }
            while (target.get(i).date.compareTo(base.get(i).date) < 0) {
                if (i != target.size() - 1)
                    target.get(i + 1).rise = target.get(i).rise * (1 + target.get(i + 1).rise / 100);
                target.remove(i);
            }
        }
    }

    /**
     * 交换两个Priceinfo信息
     *
     * @param info1
     * @param info2
     */
    public static void swapPriceInfo(final PriceInfo info1, final PriceInfo info2) {
        PriceInfo temInfo = info1;
        info1.date = info2.date;
        info1.total_netWorth = info2.total_netWorth;
        info1.rise = info2.rise;
        info1.price = info2.price;
        info2.date = temInfo.date;
        info2.total_netWorth = temInfo.total_netWorth;
        info2.rise = temInfo.rise;
        info2.price = temInfo.price;
    }

    /**
     * 计算标准差
     *
     * @param numbers
     * @param ave
     * @return
     */
    public static double standardDeviation(List<Double> numbers, double ave) {
        double sum = 0;
        for (Double num : numbers) {
            sum += (num - ave) * (num - ave);
        }
        sum = sum / (numbers.size() - 1);
        sum = Math.pow(sum, 0.5);
        return sum;
    }

    public static FofEstablishInfoEntity getFofEstablishByFundCode
            (List<FofEstablishInfoEntity> establishInfoEntities, String fundCode) throws
            ObjectNotFoundException {
        for (FofEstablishInfoEntity fofEstablishInfoEntity : establishInfoEntities) {
            if (fofEstablishInfoEntity.getFundCode().equals(fundCode))
                return fofEstablishInfoEntity;
        }
        throw new ObjectNotFoundException("can't find FofEstablishInfo of :" + fundCode);
    }

    public static FofHoldInfoEntity getFofHoldInfoByFundCode(List<FofHoldInfoEntity> entities,
                                                             String fundCode) throws
            ObjectNotFoundException {
        for (FofHoldInfoEntity fofHoldInfoEntity : entities) {
            if (fofHoldInfoEntity.getFundId().equals(fundCode)) {
                return fofHoldInfoEntity;
            }
        }
        throw new ObjectNotFoundException("can't find FofHoldInfo of:" + fundCode);
    }

    public static NetWorthEntity getNetWorthByDate(List<NetWorthEntity> entities, String date) {
        for (NetWorthEntity entity : entities) {
            if (entity.getDate().equals(date))
                return entity;
        }
        return null;
    }


}
