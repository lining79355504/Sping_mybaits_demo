package com.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Author:  lining17
 * Date :  18/05/2018
 */
public class DateUtil {


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
