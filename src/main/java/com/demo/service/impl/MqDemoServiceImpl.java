package com.demo.service.impl;

import com.demo.service.MqDemoService;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * Created by 2015-218-pc on 8/22/16.
 */
@Service("mqDemoServiceImpl")
public class MqDemoServiceImpl implements MqDemoService {


    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }



}
