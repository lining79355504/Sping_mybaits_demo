package com.demo.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mort on 06/06/2017.
 */

public class FlowControlFilter  implements Filter{

    private HashMap flowUrlMap = new HashMap();

    // @Value("#{flowControl['flow.all']}")  filter 不可用
    private int flowAll = 300 ;

    private AtomicInteger qps = new AtomicInteger();

    Logger logger = LoggerFactory.getLogger(FlowControlFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {   //启动时调用

        System.out.println("init");

        flowUrlMap.put("/hello" , 100);


    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse)response ;
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        System.out.println("doFilter");
        int currentQpsAdd = qps.incrementAndGet();
        System.out.println("current qps is : {} " + currentQpsAdd);
        logger.info("current qps is : {}" , currentQpsAdd);

        try {
            if(currentQpsAdd>flowAll){
                logger.info("over flow : {}" , currentQpsAdd );
                httpResponse.setStatus(800);
                return;

            }else if(currentQpsAdd > Integer.valueOf(String.valueOf(flowUrlMap.get(httpRequest.getRequestURI()))).intValue()){
                 logger.info(" api over flow :{} , {} , limit is {} " ,httpRequest.getContextPath() , currentQpsAdd , flowUrlMap.get(httpRequest.getContextPath()) );
                httpResponse.setStatus(800);
                return;
            }

            chain.doFilter(request , response);  // keep on going
        }catch (Exception e ){
            logger.info("doFilter exception : {}" , e);
        }finally {
            atomDecrease();
        }



    }

    @Override
    public void destroy() {   //servlet 关闭 重启 reload 时调用

        int currentQpsDestory = qps.decrementAndGet();
        System.out.println("destroy qps is : {}" + currentQpsDestory);
        logger.info("destroy qps is : {}",currentQpsDestory);

    }


    public void atomDecrease() {

        int currentQpsDestory = qps.decrementAndGet();
        System.out.println("destroy qps is : {}" + currentQpsDestory);
        logger.info("destroy qps is : {}",currentQpsDestory);

    }

}
