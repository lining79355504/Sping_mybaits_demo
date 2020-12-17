package com.demo.springCore.jdk8.function;

import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mort
 * @Description
 * @date 2020/12/17
 * 去掉你的if else
 **/
@Component
public class ProcessService {


    private Map<String, TestFunction<String, String, String, Integer>> requestFunctionMap = new HashMap<>();

    public void init() throws ServletException {
        requestFunctionMap.put("/web_api/v1/cpc/resource/launch_type", this::test);
    }

    private String test(String str, String name, Integer age) {
        return null;
    }


    public void process() {
        String str = "";
        String name = "";
        Integer age = 0;
        requestFunctionMap.get("path").apply(str, name, age);
    }

}
