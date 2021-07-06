package com.demo.log;

/**
 * @author mort
 * @Description
 * @date 2021/6/30
 **/
public class Utils {

    //判断某个类是否加载 可以判断是否引入某个版本的jar包
    public static boolean isPresent(String name) {
        try {
            Thread.currentThread().getContextClassLoader().loadClass(name);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
