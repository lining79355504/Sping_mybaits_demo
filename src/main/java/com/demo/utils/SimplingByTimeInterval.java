package com.demo.utils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Author:  lining17
 * Date :  2019-06-28
 */
public class SimplingByTimeInterval {

    private static AtomicBoolean simpledFlag = new AtomicBoolean(false);

    private static volatile long lastSimpledTime = 0;

    //最终转换为毫秒计算
    private static volatile int simpleInterval = 0;

    private static TimeUnit IntervalType;


    //算法 1s 30%采样率，1秒分3份 , 1份采样一次。
    public static SimplingByTimeInterval createSimple(double simpleIntervalRate, TimeUnit IntervalType) {
        SimplingByTimeInterval simplingByTimeInterval = new SimplingByTimeInterval();
        //TODO default 秒
        simplingByTimeInterval.setSimpleInterval((int) (100 / simpleIntervalRate));
        simplingByTimeInterval.setIntervalType(IntervalType);
        return simplingByTimeInterval;
    }


    public static boolean simpleEnable() {
        if (0 == lastSimpledTime) {
            lastSimpledTime = System.currentTimeMillis();
            return true;
        }

        switch (IntervalType) {
            case SECONDS:
                return simpleBySecondProcess();
            default:
                return simpleBySecondProcess();
        }


    }

    public static boolean simpleBySecondProcess() {

        //have simpled
        if (simpledFlag.get()) {
            simpledFlag.compareAndSet(true, false);
        }
        if (System.currentTimeMillis() - lastSimpledTime > simpleInterval) {
            return simpledFlag.compareAndSet(false, true);
        }
        return simpledFlag.compareAndSet(true, false);
    }


    public static int getSimpleInterval() {
        return simpleInterval;
    }

    public static void setSimpleInterval(int simpleInterval) {
        SimplingByTimeInterval.simpleInterval = simpleInterval;
    }

    public static TimeUnit getIntervalType() {
        return IntervalType;
    }

    public static void setIntervalType(TimeUnit intervalType) {
        IntervalType = intervalType;
    }
}
