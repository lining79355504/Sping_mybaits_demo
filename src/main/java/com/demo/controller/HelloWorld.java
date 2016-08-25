package com.demo.controller;

import com.demo.service.impl.AmsDbTestServiceImpl;
import com.demo.service.impl.MqDemoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private AmsDbTestServiceImpl amsDbTestService ;

    //引入配置
    @Value("#{mortTestConfigs['database.url']}")
    private String dbUrl ;

    @ResponseBody
    @RequestMapping("/hello")
    public String index() {
        Greeting greeting = new Greeting();
        System.out.println(greeting.sayHello());

        System.out.println(dbUrl);

        amsDbTestService.getAllAms();

        mqDemoServiceImpl.receiveMessage("oms_name");

        return "asasassa";
    }


}
