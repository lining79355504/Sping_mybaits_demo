package com.demo.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mort on 06/06/2017.
 */
public class FlowControlFilter  implements Filter{

    private AtomicInteger qps = new AtomicInteger();

    Logger logger = LoggerFactory.getLogger(FlowControlFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {   //启动时调用

        System.out.println("init");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("doFilter");
        int currentQpsAdd = qps.incrementAndGet();
        System.out.println("current qps is : {} " + currentQpsAdd);
        logger.info("current qps is : {}" , currentQpsAdd);

        try {
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
