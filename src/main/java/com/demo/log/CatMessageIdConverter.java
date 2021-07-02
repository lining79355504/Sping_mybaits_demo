package com.demo.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.dianping.cat.Cat;

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
            return Cat.getCurrentMessageId();
        }
        return "";
    }
}
