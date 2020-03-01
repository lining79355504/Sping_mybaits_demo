package com.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Author:  lining17
 * Date :  18/05/2018
 */
public class DateUtil {



    public static final int SECONDS_IN_DAY = 60 * 60 * 24;

    public static final int MILLIS_IN_MINUTE = 60 * 1000;

    public static final long MILLIS_IN_DAY = 1000L * SECONDS_IN_DAY;

    public static final String DAY_PATTERN = "yyyy-MM-dd";

    public static final String HOUR_MINUTE_PATTERN = "HH:mm";

    public static final String SECOND_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * @param minute
     * @return
     */
    public static String getLastTime(int minute) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minute);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());

    }

    /**
     * @param date
     * @param minute
     * @return
     */
    public static Date getOffsetTime(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }


    /**
     * @param date
     * @param minute
     * @return
     */
    public static String getOffsetTimeStr(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
    }

    public static long getNextDayTTL() {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
    }


    public static long getNextMonday(Date date) {

        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        int nextMondayOffset = dayOfWeek == 1 ? 1 : 9 - dayOfWeek;
        cd.add(Calendar.DAY_OF_MONTH, nextMondayOffset);
        cd.set(Calendar.HOUR_OF_DAY, 0);
        cd.set(Calendar.SECOND, 0);
        cd.set(Calendar.MINUTE, 0);
        cd.set(Calendar.MILLISECOND, 0);
        return (cd.getTimeInMillis() - System.currentTimeMillis()) / 1000;
    }

    public static long getNextMonth(Date date) {
        Calendar cd = Calendar.getInstance();

//        cd.setTime(new Date(119,9,10));  // 自定义时间设置  Date 其实时间1900  月份0-11 日 1-31
        cd.setTime(date);

        cd.add(Calendar.MONTH, 1);
        cd.set(Calendar.DAY_OF_MONTH, 1);
        cd.set(Calendar.HOUR_OF_DAY, 0);
        cd.set(Calendar.SECOND, 0);
        cd.set(Calendar.MINUTE, 0);
        cd.set(Calendar.MILLISECOND, 0);
        return (cd.getTimeInMillis() - System.currentTimeMillis()) / 1000;
    }


    public static int gapTimeToInt(long val) {
        return Integer.valueOf(String.valueOf(val));
    }


    //开始结束时间是否跨天
    public static boolean isIntraDay(long star, long end) {
        final long interval = end - star;
        return interval < MILLIS_IN_DAY
                && interval > -1L * MILLIS_IN_DAY
                && toDay(star) == toDay(end);
    }

    public static int timeStampToMinuteOrder(long time){

        Date date=new Date();
        date.setTime(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        Long dayZero = calendar.getTimeInMillis();
        return (int) ((time - dayZero) / MILLIS_IN_MINUTE);
    }

    public static String minuteOrderToDateStr(long startTime ,int minuteOrder){
        Date date=new Date();
        date.setTime(startTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        long timeStamp = minuteOrder*MILLIS_IN_MINUTE + calendar.getTimeInMillis();
        return timestampToDateStr(HOUR_MINUTE_PATTERN,timeStamp);
    }

    private static long toDay(long millis) {
        return (millis + TimeZone.getDefault().getOffset(millis)) / MILLIS_IN_DAY;
    }

    public static String timestampToDateStr(String pattern, long timestamp){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date(timestamp));
    }



    public static void main(String[] args) {

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟
        String dstr="2018-05-16 15:30:43";
        Date date = null;
        try {
            date = sdf.parse(dstr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("args = [" + getOffsetTime(date,10) + "]");

    }

}
