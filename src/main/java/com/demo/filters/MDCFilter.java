package com.demo.filters;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

/**
 * @author mort
 * @Description
 * @date 2021/4/20
 * sl4j 增加全局标识 配和cat message_id 实现全部请求查询
 *
 * sl4j 对应配置如下
  <?xml version="1.0" encoding="UTF-8" ?>
  <configuration>
      <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
          <target>System.out</target>
          <encoder>
              <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%X{UNIQUE_KEY}] [%X{IP}] [%thread] %-5level %logger{36} - %msg%n</pattern>
          </encoder>
      </appender>
      <root level="INFO">
          <appender-ref ref="STDOUT"/>
      </root>
  </configuration>
 *
 *
 **/
public class MDCFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {
            MDC.put("UNIQUE_KEY", UUID.randomUUID().toString());
            MDC.put("IP", request.getRemoteAddr());
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }

    }

    @Override
    public void destroy() {

    }
}
