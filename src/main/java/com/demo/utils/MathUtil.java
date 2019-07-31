package com.demo.utils;

import java.text.DecimalFormat;

/**
 * Author:  lining17
 * Date :  2019-07-29
 */
public class MathUtil {

    public static String PATTERN_TWO = "#.00" ;  // "#.##" 0和#的区别末尾是0是否显示

    public static String formatDouble(double d, String pattern) {
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(d);
    }

    public static String formatDouble(double d) {
        DecimalFormat df = new DecimalFormat(PATTERN_TWO);
        return df.format(d);
    }

    public static String formatPercentDouble(double d) {
        DecimalFormat df = new DecimalFormat(PATTERN_TWO);
        return df.format(d * 100) + "%";
    }
}
