package com.demo.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by mort on 01/08/2017.
 */
public class Test {


    public static void main(String[] args) {


        ApplicationContext context = new ClassPathXmlApplicationContext(
                "spring-core.xml");

        UserEntity user = (UserEntity) context.getBean("user");
        System.out.println(user.getName());
        System.out.println(user.getAge());
        System.out.println(new UserEntity().getAge());


    }
}
