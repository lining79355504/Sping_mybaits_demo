package com.demo.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * Created by 2015-218-pc on 8/22/16.
 */
public interface MqDemoService {

    public void receiveMessage(String message);

    public CountDownLatch getLatch();


}


