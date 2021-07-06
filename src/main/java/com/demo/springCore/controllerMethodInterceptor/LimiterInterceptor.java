package com.demo.springCore.controllerMethodInterceptor;

import com.demo.ratelimit.GuavaRateLimiterUtil;
import com.demo.springCore.annotation.Limit;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mort
 * @Description
 * @date 2021/7/2
 *
 * 需要注入到 RequestMappingHandlerMapping 的 interceptors中去
 *
 *
<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping">
<property name="interceptors">
<list>
<ref bean="limiterInterceptor"/>
</list>
</property>
</bean>

 or


<mvc:interceptors>   register

 **/
@Component("limiterInterceptor")
public class LimiterInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        boolean hasMethodAnnotation = handlerMethod.hasMethodAnnotation(Limit.class);
        if (hasMethodAnnotation) {
            Limit annotationInfo = handlerMethod.getMethod().getAnnotation(Limit.class);
            String name = annotationInfo.name();
            int qps = annotationInfo.qps();
            if (!"".equals(name) && qps >= 0) {
                RateLimiter rateLimiter = GuavaRateLimiterUtil.createResourceRateLimiter(name, qps);
                boolean tryAcquire = rateLimiter.tryAcquire();
                if (tryAcquire) {
                    return true;
                } else {
                    throw new Exception("rate limiter");
                }
            }
        }
        return true;
    }

}
