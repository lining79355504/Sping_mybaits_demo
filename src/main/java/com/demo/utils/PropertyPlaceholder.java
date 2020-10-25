package com.demo.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Author:  lining17
 * Date :  2018/7/2
 */
public class PropertyPlaceholder extends PropertyPlaceholderConfigurer {

    private static Map<String,String> propertyMap;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        propertyMap = new HashMap<String, String>();

        // 分布式配置中间件实现 此处可以从文件遍历 可以从服务（配置中心）拉取 远端和本地优先级策略
        // 最终远端和本地配置的交集的值进入propertyMap 即可
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            propertyMap.put(keyStr, value);
        }
    }

    public static Object getProperty(String name) {
        return propertyMap.get(name);
    }
}