package com.demo.annotataion;

import java.lang.annotation.*;

/**
 * Created by mortli on 3/22/17.
 */

@Documented
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    String value();
}
