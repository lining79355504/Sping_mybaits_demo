package com.demo.springCore.beanRegister;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.env.Environment;

/**
 * @author mort
 * @Description
 * @date 2021/6/3
 *
 * ClassPathBeanDefinitionScanner 支持自定义bean注册的扫描路径  支持自定义注解注册bean
 **/
public class MyBeanPackageScanner extends ClassPathBeanDefinitionScanner {

    public MyBeanPackageScanner(BeanDefinitionRegistry registry) {
        super(registry, true);
    }

    public MyBeanPackageScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
        super(registry, useDefaultFilters);
    }


}
