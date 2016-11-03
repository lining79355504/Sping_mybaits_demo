package com.demo.controller;

public class Greeting {
    public String sayHello() {
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