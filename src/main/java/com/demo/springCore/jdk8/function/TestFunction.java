package com.demo.springCore.jdk8.function;

/**
 * @author mort
 * @Description
 * @date 2020/12/17
 **/
@FunctionalInterface
public interface TestFunction<R, T, V, S> {
    R apply(T t, V v, S s);
}
