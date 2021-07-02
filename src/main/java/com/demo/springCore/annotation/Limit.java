package com.demo.springCore.annotation;

/**
 * @author mort
 * @Description
 * @date 2021/7/2
 **/
public @interface Limit {

    String name() default "";

    int qps() default -1;
}
