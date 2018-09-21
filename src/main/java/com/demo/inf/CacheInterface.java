package com.demo.inf;

import java.util.HashMap;

/**
 * Author:  lining17
 * Date :  2018/9/12
 */
public interface CacheInterface<K,V>{

        /**
         * 返回要更新的值
         * @return
         */
        public abstract HashMap<K,V> cache(K key);

}
