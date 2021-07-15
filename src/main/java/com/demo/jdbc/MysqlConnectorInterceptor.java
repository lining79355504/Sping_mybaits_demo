package com.demo.jdbc;

import com.alibaba.fastjson.JSON;
import com.mysql.cj.MysqlConnection;
import com.mysql.cj.Query;
import com.mysql.cj.interceptors.QueryInterceptor;
import com.mysql.cj.log.Log;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.protocol.ServerSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.function.Supplier;

/**
 * @author mort
 * @Description
 * @date 2021/7/15
 * <p>
 * mysql-conntector-java  interceptor  拦截器
 * <p>
 * 在jdbc连接字符中后添加，mysql-conntector-java.8  queryInterceptors=com.demo.jdbc.MysqlConnectorInterceptor
 * <p>
 * 参照  com.mysql.cj.conf.PropertyKey#queryInterceptors
 * <p>
 * Class.forName("com.mysql.jdbc.Driver", true, ClassUtils.getDefaultClassLoader());  会执行静态方法类的静态方法
 * <p>
 * com.mysql.cj.conf.ConnectionUrlParser#processKeyValuePattern(java.util.regex.Pattern, java.lang.String)
 **/

public class MysqlConnectorInterceptor implements QueryInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(MysqlConnectorInterceptor.class);


    @Override
    public QueryInterceptor init(MysqlConnection conn, Properties props, Log log) {
        return this;
    }

    @Override
    public <T extends Resultset> T preProcess(Supplier<String> sql, Query interceptedQuery) {
        logger.info("MysqlConnectorInterceptor is {} , time {}", sql.get(), System.currentTimeMillis());
        return null;
    }

    @Override
    public boolean executeTopLevelOnly() {
        return false;
    }

    @Override
    public void destroy() {

    }

    @Override
    public <T extends Resultset> T postProcess(Supplier<String> sql, Query interceptedQuery, T originalResultSet, ServerSession serverSession) {
        //此处和执行的sql 不是串行的 是并行打印的
//        logger.info("end time is {}, result {} ", System.currentTimeMillis() , JSON.toJSONString(originalResultSet));
        logger.info("end time is {}", System.currentTimeMillis());
        return null;
    }
}
