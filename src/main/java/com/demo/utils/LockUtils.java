package com.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.UUID;

/**
 * Author:  mort
 * Date :  26/04/2018
 */
@Service
public class LockUtils {


    private static final Logger logger = LoggerFactory.getLogger(LockUtils.class);

    private static JedisPool pool = null;
    private static Jedis jedis = null;
    @Value("#{redisConf['redisConf.host']}")
    private static String host;
    @Value("#{redisConf['redisConf.port']}")
    private static int port;
    @Value("#{redisConf['redisConf.passwd']}")
    private static String auth;


    @Autowired
    private JedisPoolConfig poolConfig;


    LockUtils() {
        this.pool = new JedisPool(poolConfig, host, port, 10000);
        this.jedis = pool.getResource();
    }

    //  一种一直持续等待拿到锁 , 持续等待拿到锁。

    // 一种锁被抢占 又业务自己重试 获取。

    /**
     * @param key
     * @param expire seconds
     * @return
     */
    public static boolean getLock(String key, int expire, int waitTime) {

        long startTime = System.currentTimeMillis();
        while (!getLockLoop(key, expire)) {
            long waitEndTime = System.currentTimeMillis();
            if (waitEndTime - startTime > waitTime * 1000) return false;
        }

        return true;

    }

    /**
     * @param key
     * @param expire
     * @return 过期时间戳 + _ + UUID   UUID解决多线程下毫秒并发问题
     */
    public static boolean getLockLoop(String key, int expire) {


        long expireTime = System.currentTimeMillis() + expire * 1000;

        String value = String.valueOf(expireTime) + "_" + UUID.randomUUID();
        try {
            Long ret = jedis.setnx(key, value);
            if (0 == ret.longValue()) {
                jedis.expire(key, expire);
                return true;
            }

            // key already set
            if (1 == ret.longValue()) {

                String old = jedis.get(key);

                String[] strings = old.split("_");
                long oldExpireTimeStamp = Long.valueOf(strings[0]);

                if (oldExpireTimeStamp < System.currentTimeMillis()) {

                    if (old == jedis.getSet(key, value)) {
                        jedis.expire(key, expire);        // UUID 作用
                        return true;
                    }
                }

                return false;
            }
        } catch (Exception e) {
            logger.error("exception {}", e);
            return false;
        }

        return false;

    }


}
