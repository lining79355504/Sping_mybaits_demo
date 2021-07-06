package com.demo.service.impl;

import com.demo.service.MqDemoService;
import com.demo.springCore.annotation.Limit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * Created by 2015-218-pc on 8/22/16.
 */
@Service("mqDemoServiceImpl")
public class MqDemoServiceImpl implements MqDemoService {

    //引入配置
    @Value("#{mortTestConfigs['database.url']}")
    private String dbUrl;


    private CountDownLatch latch = new CountDownLatch(1);

    @Override
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

    @Override
    public CountDownLatch getLatch() {
        return latch;
    }

    @Override
    @Limit(name = "getDbUrl" , qps = 1)
    public String getDbUrl(){
        return dbUrl;
    }
}
