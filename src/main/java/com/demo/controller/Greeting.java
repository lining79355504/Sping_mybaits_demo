package com.demo.controller;

import com.demo.annotataion.MyAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.channels.NotYetConnectedException;

@Controller
public class Greeting {

    Logger logger = LoggerFactory.getLogger(Greeting.class);

    @ResponseBody
    @RequestMapping(value = "/helloWorld" , method = RequestMethod.GET )
    @MyAnnotation(value = "helloWorld")
    public String sayHello(HttpServletRequest req, HttpServletResponse res) {
        logger.info("test asasa{} ",11212);
        logger.error("test error {} ,{},{},{},{},{},{},{},{} " , 3,3,3,3,3,3,3,3);
        return "Hello world!";
    }


    public static void main(String[] args) {

        int intArray[];
        String str = "GZ1" ;
        //if(str == "GZ1"){
        if(str.equals("GZ1")){

            System.out.println("equal");

        }else{

            System.out.println("not equal ");

        }
        try {
            throwsIo();
        } catch (Exception e) {
            e.printStackTrace();
        }

        double i = Double.parseDouble(String.valueOf((double) 0.5));
        double j = 1 - i;
        if(i<j){

        }
    }

    public static void throwsIo() throws IOException {
        throw new NotYetConnectedException();
    }
}