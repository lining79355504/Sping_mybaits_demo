package com.demo.springCore.httpInvoker;

import org.aopalliance.intercept.MethodInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.remoting.httpinvoker.HttpInvokerClientInterceptor;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author mort
 * @Description
 * @date 2021/7/6
 **/
@Component
public class HttpInvokerCustomerIntercept implements BeanPostProcessor {

     private static final Logger logger = LoggerFactory.getLogger(HttpInvokerCustomerIntercept.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof HttpInvokerClientInterceptor) {
            ProxyFactoryBean pfb = new ProxyFactoryBean();
            pfb.setTarget(bean);
            pfb.setAutodetectInterfaces(false);

            NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
            // method 匹配
            advisor.addMethodName("invoke");
            advisor.setAdvice((MethodInterceptor) invocation -> {
                String methodName = invocation.getMethod().getName();
                logger.info("HttpInvokerCustomerIntercept 开始执行 {} start", methodName);
                Object result = invocation.getMethod().invoke(invocation.getThis(), invocation.getArguments());
                logger.info("HttpInvokerCustomerIntercept 结束执行 {} end", methodName);
                return result;
            });

            pfb.addAdvisor(advisor);

            return pfb.getObject();
        }
        return bean;
    }
}
