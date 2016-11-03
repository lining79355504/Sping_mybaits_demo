package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.service.impl.AmsDbTestServiceImpl;
import com.demo.service.impl.MqDemoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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


    public static void main(String[] args) {


        String str ="{4097:28, 1583:34, 1395:22, 4180:22, 1448:26, 683:22, 1589:26, 1588:22, 1267:22,10004851:28,1597:28,1596:28,822:22,757:26,869:26,1419:22,1449:26,3712:26,3702:26,3694:26,3683:26,1457:26,1456:26,1455:26,1454:26,1453:26,1452:26,1451:26,1450:26}" ;
        //Map<String ,String> categoryMap : (Map) JSON.parseArray(str);

        //JSONObject  jasonObject = JSONObject.parseObject(str);
        //Map map = (Map)jasonObject;
        //System.out.println(map.get(String.valueOf(4097)));

        String shopCids = "#682# #1395# #724#";

        String shopCid[] = shopCids.split("#");
        JSONObject jasonObject = JSONObject.parseObject(str);
        Map categoryMap = (Map)jasonObject;
        System.out.println(categoryMap.get("4097").getClass());

        List aList = new ArrayList();
        aList.add(22991416655268L);
        aList.add(22991416655269L);

        System.out.println(aList);

        List<String>  sList = (List<String>) aList;


        System.out.println(sList.get(0).getClass().toString());


    }


}
