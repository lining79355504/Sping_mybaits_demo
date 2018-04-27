package com.demo.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author:  mort
 * Date :  26/01/2018
 */
public class ExecutorServiceManagerUtils {

    private ExecutorServiceManagerUtils(){}

    private volatile static ThreadPoolExecutor singleton = new ThreadPoolExecutor(100, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

    public static ExecutorService getSingleton() {
        if (null == singleton) {
            synchronized (singleton) {
                if (null == singleton) {
                    return new ThreadPoolExecutor(100, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
                }

            }

        }

        return singleton;
    }
}

