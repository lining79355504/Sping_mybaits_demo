package com.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.aspectj.lang.annotation.Pointcut;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Author:  lining17
 * Date :  2020-04-14
 */
@Aspect
@Component
public class PermissionAspect {

//    @Autowired
//    private MetricConfigService metricConfigService;

//    @Pointcut("execution(* com.sankuai.raptor.mobile.web.controller.metric.MetricController.delete*(..))" +
//            "|| execution(* com.sankuai.raptor.mobile.web.controller.metric.MetricController.save*(..)))")
    @Pointcut("@annotation(com.demo.annotataion.MyAnnotation)")
    public void authCheck(){

    }

    @Around("authCheck()")
    public Object check(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取httpServerRequest
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Object[] args = joinPoint.getArgs();
        int appId = getAppId(args);
//        String userMis = UserInfoHelper.fetchUserMis(request);
//        boolean permission = metricConfigService.checkAppPermissionById(appId, userMis);
//
//        if (permission) {
//            return joinPoint.proceed();
//        }

//        Result ret = new Result();
//        ret.setMessage("没有权限！请前往cat申请APP权限。");
//        ret.setCode(Result.ERROR_CODE);
        return new Object();
    }

    private int getAppId(Object[] args) {
        int appId = 0;

//        for (Object arg : args) {
//            if (arg instanceof MetricRequestDTO) {
//                appId = ((MetricRequestDTO) arg).getAppId();
//            } else if (arg instanceof Map) {
//                appId = (int) ((Map) arg).get("appId");
//            } else if (arg instanceof CompoundMetricEditDTO) {
//                appId = ((CompoundMetricEditDTO) arg).getAppId();
//            } else if (arg instanceof TagValueEditDTO) {
//                appId = ((TagValueEditDTO) arg).getAppId();
//            }
//        }
        return appId;
    }

}
