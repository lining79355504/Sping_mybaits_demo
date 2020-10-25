package com.demo.bean;


import org.springframework.stereotype.Component;

/**
 * Created by mort on 01/08/2017.
 */
@Component("userEntity")
public class UserEntity {

    private String name ;

    private Integer age ;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
