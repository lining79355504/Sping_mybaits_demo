package com.demo.servletContextLister;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by mort on 15/05/2017.
 */
public class MyServletContextListerTest implements ServletContextListener {


    private static final Logger  logger = LoggerFactory.getLogger(MyServletContextListerTest.class);

    //context servlet 启动时初始化一次持续监听    不同filter filter会对只初始化一次 但是对所有请求都有作用
    public void contextInitialized(ServletContextEvent event){


        logger.info("event = {}", event);

        System.out.println("event = [" + JSON.toJSONString(event) + "]");

    }

    //  spring 有自己的context listener
    //监听两个关键事件 初始话和销毁时间  对servletContext 属性的操作
    /*
    * ServletRequestAttributeListener  监听Requset中的属性操作

      HttpSessionAttributeListener  监听Session中的属性操作   统计在线人数 监听http session连接创建

      可以做一些启动时缓存的初始化  datasource的初始化  一般不用
    *
    *
    *
    *
    * */

    // 销毁时调用该方法
    public void contextDestroyed(ServletContextEvent sce) {

        logger.info("sce = {}", sce);

        System.out.println("sce = [" + JSON.toJSONString(sce) + "]");

    }



}
