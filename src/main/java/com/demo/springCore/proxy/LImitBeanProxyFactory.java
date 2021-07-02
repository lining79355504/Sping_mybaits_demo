package com.demo.springCore.proxy;

import com.demo.ratelimit.GuavaRateLimiterUtil;
import com.demo.springCore.annotation.Limit;
import com.google.common.util.concurrent.RateLimiter;
import org.aopalliance.intercept.MethodInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author mort
 * @Description
 * @date 2021/5/20
 * <p>
 * <p>
 * ProxyFactoryBean 创建spring的bean代理
 **/
@Component
public class LImitBeanProxyFactory implements BeanPostProcessor {


    private static final Logger logger = LoggerFactory.getLogger(BeanProxyByProxyFactory.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        ProxyFactoryBean pfb = new ProxyFactoryBean();
        pfb.setTarget(bean);
        pfb.setAutodetectInterfaces(false);

        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
        // method 匹配
        advisor.addMethodName("*");
        advisor.setAdvice((MethodInterceptor) invocation -> {
//                String methodName = invocation.getMethod().getName();
//                logger.info("BeanProxyByProxyFactory 开始执行 {} start", methodName);
//                Object result = invocation.getMethod().invoke(invocation.getThis(), invocation.getArguments());
//                logger.info("BeanProxyByProxyFactory 结束执行 {} end", methodName);

            boolean annotation = invocation.getMethod().isAnnotationPresent(Limit.class);
            if (annotation) {
                Limit annotationInfo = invocation.getMethod().getAnnotation(Limit.class);
                String name = annotationInfo.name();
                int qps = annotationInfo.qps();
                if (!"".equals(name) && qps >= 0) {
                    RateLimiter rateLimiter = GuavaRateLimiterUtil.createResourceRateLimiter(name, qps);
                    boolean tryAcquire = rateLimiter.tryAcquire();
                    if (tryAcquire) {
                        Object result = invocation.getMethod().invoke(invocation.getThis(), invocation.getArguments());
                        return result;
                    } else {
                        throw new Exception("rate limiter");
                    }
                } else {
                    return invocation.getMethod().invoke(invocation.getThis(), invocation.getArguments());
                }

            }

            return null;

        });

        pfb.addAdvisor(advisor);
        return pfb.getObject();

//        return bean;
    }
}
