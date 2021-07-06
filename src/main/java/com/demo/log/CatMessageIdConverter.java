package com.demo.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.lang.reflect.Method;

/**
 * @author mort
 * @Description
 * @date 2021/6/30
 * <p>
 * public class ThreadConverter extends ClassicConverter {
 * <p>
 * public String convert(ILoggingEvent event) {
 * return event.getThreadName();
 * }
 **/

public class CatMessageIdConverter extends ClassicConverter {


    @Override
    public String convert(ILoggingEvent event) {
        if (Utils.isPresent("com.dianping.cat.Cat")) {
            try {
//                Cat.getCurrentMessageId();  反射 静态方法调用 带参数调用
                Class<?> catClass = Class.forName("com.dianping.cat.Cat");
                Method method = catClass.getMethod("getCurrentMessageId");
                Object result = method.invoke(null);
                return String.valueOf(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
