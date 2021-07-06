package com.demo.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author:  mort
 * Date :  26/01/2018
 */
public class ExecutorServiceManagerUtils {

     private static final Logger logger = LoggerFactory.getLogger(ExecutorServiceManagerUtils.class);

    //使用 ThreadPoolExecutor  避免是Executors  不能定义任务queue大小 ， 容易出现 任务queue过大 oom
    private volatile static ThreadPoolExecutor singleton = new ThreadPoolExecutor(100, 800, 60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(200), new NameThreadFactory("cat-common"));

    private ExecutorServiceManagerUtils() {
    }

    public static ExecutorService getSingleton() {
        if (null == singleton) {
            synchronized (singleton) {
                if (null == singleton) {
                    return new ThreadPoolExecutor(100, 800, 60L, TimeUnit.SECONDS,
                            new LinkedBlockingQueue<Runnable>(), new NameThreadFactory("cat-common"));
                }
            }
        }
        return singleton;
    }

    static class NameThreadFactory implements ThreadFactory {

        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        NameThreadFactory(String name) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = name + "-pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {

            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;

        }
    }


    //异步获取结果
    private <T> T getFutureResult(CompletableFuture<T> future) {
        try {
            return future.get();
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }


}

