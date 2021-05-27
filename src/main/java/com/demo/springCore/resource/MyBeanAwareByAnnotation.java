package com.demo.springCore.resource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author mort
 * @Description
 * @date 2021/5/21
 **/
@Component
public class MyBeanAwareByAnnotation {

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ApplicationContext applicationContext;

    public void useBeanFactory() {
        System.out.println(beanFactory.getBean("myBeanAwareByAnnotation"));
    }

    public void useResourceLoader() {
        System.out.println(resourceLoader.getResource("classpath:src/main/resources/application.xml"));
    }

    public void useApplicationEventPublisher() {
        System.out.println(this.applicationEventPublisher);
    }

    public void useApplicationContext() {
        System.out.println(applicationContext.getMessage("Message", null, "Not exist", Locale.JAPAN));
    }

}
