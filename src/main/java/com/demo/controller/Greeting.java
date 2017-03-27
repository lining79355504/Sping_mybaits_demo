package com.demo.controller;

import com.demo.annotataion.MyAnnotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class Greeting {

    @ResponseBody
    @RequestMapping("/helloWorld")
    @MyAnnotation(value = "helloWorld")
    public String sayHello(HttpServletRequest req, HttpServletResponse res) {
        return "Hello world!";
    }


    public static void main(String[] args) {

        String str = "GZ1" ;
        //if(str == "GZ1"){
        if(str.equals("GZ1")){

            System.out.println("equal");

        }else{

            System.out.println("not equal ");

        }
    }
}