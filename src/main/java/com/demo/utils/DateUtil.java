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
     * @param date
     * @param minute
     * @return
     */
    public static Date getOffsetTime(Date date,int minute){
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
    public static String getOffsetTimeStr(Date date, int minute){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
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
