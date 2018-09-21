package com.demo.utils;

import com.demo.cacheDo.CacheDO;
import com.demo.inf.CacheInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author:  lining17
 * Date :  2018/9/10
 */
public class CacheService<K,V> {


    private static final Logger logger = LoggerFactory.getLogger(CacheService.class);

    private static final ConcurrentHashMap<Object , CacheDO> bizHashMap = new ConcurrentHashMap();



    public  V get(K key, int cacheTime , CacheInterface cacheInterface){

        if (null == bizHashMap.get(key) ||
                (System.currentTimeMillis() / 1000 - bizHashMap.get(key).getTime() > cacheTime)) {
            set(key, cacheInterface);
        }
        return (V) bizHashMap.get(key).getObject();
    }


    public void set(K key , CacheInterface cacheInterface){
        CacheDO <V> cacheDO = new CacheDO<V>();
        cacheDO.setObject((V) cacheInterface.cache(key).get(key));
        cacheDO.setTime(System.currentTimeMillis()/1000);
        logger.info("cacheDO value  {}",cacheDO);
        bizHashMap.put(key,cacheDO) ;
    }


//    static {
//
//        new Thread();
//    }

}


