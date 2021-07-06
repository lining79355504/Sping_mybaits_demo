package com.demo.log;

import ch.qos.logback.classic.PatternLayout;

/**
 * @author mort
 * @Description
 * @date 2021/6/30
 **/
public class MyLogbackPatternLayout extends PatternLayout {


    static {
        defaultConverterMap.put("messageId", CatMessageIdConverter.class.getName());
    }


    public static void main(String[] args) {
        Utils.isPresent("com.demo.log.MyPatternLayout");
        Utils.isPresent("ch.qos.logback.core.pattern.PatternLayoutBase");
    }


}
