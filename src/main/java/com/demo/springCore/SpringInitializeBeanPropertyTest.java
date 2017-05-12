package com.demo.springCore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by mort on 12/05/2017.
 */
@Service  // service 才能识别context component-scan制定的目录 logback 不配置的话 看不到spring内部debug的log bean的初始化和创建过程
public class SpringInitializeBeanPropertyTest implements InitializingBean, BeanFactoryPostProcessor {



    Logger logger = LoggerFactory.getLogger(SpringInitializeBeanPropertyTest.class);


    private String str;

    public void SpringInitializeBeanPropertyTest(String str ){
        this.str = str ;
    }

    //after create bean  // method  invoke
    @Override
    public void afterPropertiesSet() throws Exception {


        logger.info("i am afterPropertiesSet implement InitializingBean !");


    }

    /**
     *
     * do  after finished creating bean
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        logger.info("i am postProcessBeanFactory implement BeanFactoryPostProcessor !");

    }

}
