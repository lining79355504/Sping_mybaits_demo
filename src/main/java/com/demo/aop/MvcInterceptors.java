package com.demo.aop;

/**
 * Created by mortli on 3/28/17.
 */

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 记录信息:</br> 访问时间</br>Controller路径</br>对应方法名</br>请求参数信息</br>请求相对路径</br>请求处理时长
 *
 * @author suoer
 *
 */

public class MvcInterceptors implements HandlerInterceptor{


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式  线程非安全 使用threadLocal  实现线程安全
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        if (handler instanceof HandlerMethod) {
            StringBuilder sb = new StringBuilder(1000);

            sb.append("-----------------------").append(df.format(new Date()))
                    .append("-------------------------------------\n");
            HandlerMethod h = (HandlerMethod) handler;
            sb.append("Controller: ").append(h.getBean().getClass().getName()).append("\n");
            sb.append("Method    : ").append(h.getMethod().getName()).append("\n");
            sb.append("Params    : ").append(getParamString(request.getParameterMap())).append("\n");
            sb.append("URI       : ").append(request.getRequestURI()).append("\n");
            System.out.println(sb.toString());
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private String getParamString(Map<String, String[]> map) {
        StringBuilder sb = new StringBuilder();
        for(Entry<String,String[]> e:map.entrySet()){
            sb.append(e.getKey()).append("=");
            String[] value = e.getValue();
            if(value != null && value.length == 1){
                sb.append(value[0]).append("\t");
            }else{
                sb.append(Arrays.toString(value)).append("\t");
            }
        }
        return sb.toString();
    }
}
