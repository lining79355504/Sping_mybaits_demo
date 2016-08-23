package com.demo.controller;

import com.demo.service.impl.MqDemoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 2015-218-pc on 8/18/16.
 */

@Controller
public class HelloWorld {

    @Autowired
    private MqDemoServiceImpl mqDemoServiceImpl ;

    @ResponseBody
    @RequestMapping("/hello")
    public String index() {
        Greeting greeting = new Greeting();
        System.out.println(greeting.sayHello());

        mqDemoServiceImpl.receiveMessage("oms_name");

        return "asasassa";
    }


}
