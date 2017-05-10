package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.annotataion.MyAnnotation;
import com.demo.service.impl.AmsDbTestServiceImpl;
import com.demo.service.impl.MqDemoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
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


    @Autowired
    protected RedisTemplate<String, String> redisTemplate;



    //引入配置
    @Value("#{mortTestConfigs['database.url']}")
    private String dbUrl ;

    /*
        @RequestParam Map<String, String> params   get post 都可已接收 全部的参数转为一个map
    *    public void export(@RequestParam Map<String, String> params ,HttpServletResponse response){
    *
    *    }
    * */
    @ResponseBody
    @RequestMapping("/hello")
    @MyAnnotation(value = "hello")
    public String index(HttpServletRequest req, HttpServletResponse res) {
        Greeting greeting = new Greeting();
        //System.out.println(greeting.sayHello());

        System.out.println(dbUrl);

        //amsDbTestService.getAllAms();

        //mqDemoServiceImpl.receiveMessage("oms_name");


        //spring-data-redis set key value
        redisTemplate.executePipelined(new RedisCallback<Object>() {


            public Object doInRedis(RedisConnection connection) throws DataAccessException {

                connection.set(redisTemplate.getStringSerializer().serialize("user.uid." + 121232321),
                        redisTemplate.getStringSerializer().serialize("mort"));
                return null;
            }
        });



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
