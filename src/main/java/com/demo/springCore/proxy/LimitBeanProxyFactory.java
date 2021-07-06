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

import java.lang.reflect.Method;

/**
 * @author mort
 * @Description
 * @date 2021/5/20
 * <p>
 * <p>
 * ProxyFactoryBean 创建spring的bean代理
 *
 * 熔断 降级 故障注入 流量探测 恢复降级  方法级别限流 打点 性能分析
 *
 **/
@Component
public class LimitBeanProxyFactory implements BeanPostProcessor {   //这种方式获取不到 controller的bean注解


    private static final Logger logger = LoggerFactory.getLogger(LimitBeanProxyFactory.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(!hasLimitAnnotation(bean)){
            return bean;
        }

        ProxyFactoryBean pfb = new ProxyFactoryBean();
        pfb.setTarget(bean);
        pfb.setAutodetectInterfaces(false);

        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
        // method 匹配
        advisor.addMethodName("*");
        advisor.setAdvice((MethodInterceptor) invocation -> {

            boolean annotation = invocation.getMethod().isAnnotationPresent(Limit.class);
            if (annotation) {
                Limit annotationInfo = invocation.getMethod().getAnnotation(Limit.class);
                String name = annotationInfo.name();
                int qps = annotationInfo.qps();
                if (!"".equals(name) && qps >= 0) {
                    RateLimiter rateLimiter = GuavaRateLimiterUtil.createResourceRateLimiter(name, qps);
                    boolean tryAcquire = rateLimiter.tryAcquire();
                    if (tryAcquire) {
                        return invocation.getMethod().invoke(invocation.getThis(), invocation.getArguments());
                    } else {
                        logger.info("rate limiter {} , qps {}", name, qps);
                        throw new Exception("rate limiter" + name);
                    }
                }
            }
            return invocation.getMethod().invoke(invocation.getThis(), invocation.getArguments());

        });

        pfb.addAdvisor(advisor);
        return pfb.getObject();
    }

    private boolean hasLimitAnnotation(Object bean) {
        Method[] methods = bean.getClass().getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Limit.class)) {
                return true;
            }
        }
        return false;
    }
}
