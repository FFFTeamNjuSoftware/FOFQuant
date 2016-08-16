package util;


import java.util.Calendar;

/**
 * Created by Daniel on 2016/8/16.
 * 常用的日期操作
 */
public class CalendarOperate {

    /**
     * 根据字符串返回一个calendar实例
     *
     * @param date
     * @return
     */
    public static Calendar getCalendarByString(String date) {
        Calendar calender = Calendar.getInstance();
        String[] infos = date.split("-");
        int year = new Integer(infos[0]);
        int month = new Integer(infos[1]) - 1;
        int day = new Integer(infos[2]);
        calender.set(year, month, day);
        return calender;
    }

    /**
     * @param cal calender实例
     * @return 返回xxxx-xx-xx格式的字符串日期
     */
    public static String formatCalender(Calendar cal) {
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);
        return String.format("%4d-%02d-%02d", year, month, day);
    }

    /**
     * 返回当前日期的下一天
     *
     * @param date
     * @return
     */
    public static String nextDay(String date) {
        Calendar calendar = getCalendarByString(date);
        do {
            calendar.add(Calendar.DATE, 1);
        }
        while (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar
                .DAY_OF_WEEK) == Calendar.SUNDAY);
        return formatCalender(calendar);
    }



}
