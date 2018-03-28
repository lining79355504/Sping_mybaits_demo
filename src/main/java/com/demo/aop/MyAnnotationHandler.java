package com.demo.aop;



import com.alibaba.fastjson.JSON;
import com.demo.annotataion.MyAnnotation;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Created by mortli on 3/22/17.
 */
public class MyAnnotationHandler implements MethodInterceptor {


    private static final Logger logger = LoggerFactory.getLogger(MyAnnotationHandler.class);

    // 获取session  登录信息配合新的注解 实现权限控制   权限控制台
    public Object invoke(MethodInvocation invocation) throws Throwable {

        Object[] ars = invocation.getArguments();
        for (Object item:ars) {
            if(item instanceof HttpServletRequest){
                logger.info("------------this is a HttpServletRequest Parameter------------ {} ", ((HttpServletRequest) item).getParameterMap());
                logger.info("------------this is a HttpServletRequest Parameter map ------------ {} " , ((HttpServletRequest) item).getParameterMap());
                logger.info("------------this is a HttpServletRequest Method------------ {} ", ((HttpServletRequest) item).getMethod());
                logger.info("------------this is a HttpServletRequest URL------------  {}",  ((HttpServletRequest) item).getRequestURI());
            }
            }
        // 判断是否加了@MyAnnotation 注解
        if(invocation.getMethod().isAnnotationPresent(MyAnnotation.class)){
            logger.info("----------this method is added @MyAnnotation-------------------------");
        }
        //执行被拦截的方法，切记，如果此方法不调用，则被拦截的方法不会被执行。
        return invocation.proceed();
    }
}
