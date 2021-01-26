package com.demo.utils.annotation;

import java.lang.annotation.*;

/**
 * @author mort
 * @Description
 * @date 2021/1/26
 * 自定义注解忽略字段
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SerializeFilter {
    String value();
}
