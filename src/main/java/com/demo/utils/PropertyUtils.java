package com.demo.utils;

import java.io.*;
import java.util.Properties;

/**
 * @author mort
 * @Description
 * @date 2020/11/27
 * 非spring property 读取
 **/
public class PropertyUtils {

    private static final String PROPERTIES_FILE = "/META-INF/app.properties";

    /**
     * classPath 根目录相当于在classpath  下的相对路径
     * @param key
     * @param path
     * @return
     */
    public static String get(String key, String path) {
        //加载配置文件
        Properties pro = new Properties();
        InputStream in = null;
        try {
            in = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
            try {
                pro.load(in);
                return pro.getProperty(key);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void set(String key, String value, String path) {
        Properties pro = new Properties();
        FileOutputStream file = null;
        try {
            file = new FileOutputStream(path);
            pro.put(key, value);
            try {
                pro.store(file, "系统配置修改"); //这句话表示重新写入配置文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
