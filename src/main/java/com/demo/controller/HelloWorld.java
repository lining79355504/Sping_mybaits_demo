package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 2015-218-pc on 8/18/16.
 */

@Controller

public class HelloWorld {


    @ResponseBody
    @RequestMapping("/hello")
    public String index() {
        Greeting greeting = new Greeting();
        System.out.println(greeting.sayHello());
        return "asasassa";
    }


}
