package com.demo.springCore.beanRegister;

import org.mybatis.spring.mapper.ClassPathMapperScanner;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;

import static org.springframework.util.Assert.notNull;

/**
 * @author mort
 * @Description
 * @date 2021/6/3
 * @see org.mybatis.spring.mapper.MapperScannerConfigurer
 *
 *  自定义中间件 的自定义注解中使用    配合自定义的nameSpace + 设计模式 实现类似rapptor的 namespace配置
 *
 *
 **/
public class MyBeanPackageDefinition implements BeanDefinitionRegistryPostProcessor, InitializingBean, ApplicationContextAware, BeanNameAware {


    private ApplicationContext applicationContext;

    private String beanName;

    private String basePackage;

    private Class<? extends Annotation> annotationClass;

    private boolean addToConfig = true;

    private Class<?> markerInterface;



    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        notNull(this.basePackage, "Property 'basePackage' is required");
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

//
//        namespace 注册bean 常用
//
//        BeanDefinitionBuilder definitionBuilder =
//                BeanDefinitionBuilder.genericBeanDefinition(JndiObjectFactoryBean.class);
//        definitionBuilder.addPropertyValue("jndiName", "jdbc/" + "test");
//        definitionBuilder.addConstructorArgReference();
//        definitionBuilder.addConstructorArgValue();
//        registry.registerBeanDefinition("dataSourceName", definitionBuilder.getBeanDefinition());


        MyBeanPackageScanner scanner = new MyBeanPackageScanner(registry);
//        scanner.setResourceLoader(this.applicationContext);
        scanner.scan(StringUtils.tokenizeToStringArray(this.basePackage, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public Class<? extends Annotation> getAnnotationClass() {
        return annotationClass;
    }

    public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }

    public boolean isAddToConfig() {
        return addToConfig;
    }

    public void setAddToConfig(boolean addToConfig) {
        this.addToConfig = addToConfig;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }
}
