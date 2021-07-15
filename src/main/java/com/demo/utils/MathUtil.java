package com.demo.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Author:  lining17
 * Date :  2019-07-29
 * <p>
 * String.valueOf(double) 会把double 解析为科学计数法
 * <p>
 * 如果计算追求经度过高 请使用BigDecimal
 */
public class MathUtil {

    public static String PATTERN_TWO = "#.00";  // "#.##" 0和#的区别末尾是0是否显示

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

    //Number 类型累加
    public static <T extends Number> void add(Supplier<T> supplier, Supplier<T> supplierOld, Consumer<T> consumer) {
        T t = supplier.get();
        if (null == t) {
            if (t instanceof Integer) {
                consumer.accept((T) new Integer(0));
            } else if (t instanceof BigDecimal) {
                consumer.accept((T) new BigDecimal(0));
            } else if (t instanceof Long) {
                consumer.accept((T) new Long(0));
            }
        }

        if (null == supplierOld.get()) {
            consumer.accept(t);
        } else {
            if (t instanceof Integer) {
                consumer.accept((T) new Integer(supplierOld.get().intValue() + t.intValue()));
            } else if (t instanceof BigDecimal) {
                consumer.accept((T) new BigDecimal(supplierOld.get().doubleValue() + t.doubleValue()));
            } else if (t instanceof Long) {
                consumer.accept((T) new Long(supplierOld.get().longValue() + t.longValue()));
            }
        }
    }
}
