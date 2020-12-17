package com.demo.design.pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author mort
 * @Description
 * @date 2020/12/17
 * 去掉相同参数的方法 出现大量的if else
 * 利用 Java8 的 BiConsumer 实现
 **/
public class MethodRouter implements InitializingBean , Filter {

     private static final Logger logger = LoggerFactory.getLogger(MethodRouter.class);

    private Map<String, BiConsumer<ServletRequest, ServletResponse>> routerMap = new HashMap<>();

    public void init() throws ServletException {
        routerMap.put("web_api/v1/cpc/resource/launch_type", this::launchTypeFilter);
    }

    private void launchTypeFilter(ServletRequest request, ServletResponse response) {


    }

    @Override
    public void afterPropertiesSet() throws Exception {



    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String path = ((HttpServletRequest) request).getRequestURI();
        chain.doFilter(request, response);

        // router
        BiConsumer<ServletRequest, ServletResponse> router = routerMap.get(path);
        if (null == router) {
            logger.error("no valid config find ");
            return;
        }

        router.accept(request, response);
    }

    @Override
    public void destroy() {

    }
}
