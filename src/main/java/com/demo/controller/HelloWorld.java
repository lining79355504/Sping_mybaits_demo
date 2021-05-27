package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.annotataion.MyAnnotation;
import com.demo.dao.StockDealJson;
import com.demo.dao.StockDetail;
import com.demo.mapper.StockDealJsonMapper;
import com.demo.mapper.StockDetailMapper;
import com.demo.service.impl.AmsDbTestServiceImpl;
import com.demo.service.impl.MqDemoServiceImpl;
import com.demo.springCore.resource.MyBeanAwareByAnnotation;
import com.demo.utils.PropertyUtils;
import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

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

    @Autowired
    private StockDetailMapper stockDetailMapper;

    @Autowired
    private StockDealJsonMapper stockDealJsonMapper;

    @Autowired
    private MyBeanAwareByAnnotation myBeanAwareByAnnotation;


    private AtomicInteger redisQps = new AtomicInteger();

    //引入配置
    @Value("#{mortTestConfigs['database.url']}")
    private String dbUrl ;

    @Value("#{flowControl['flow.all']}")
    private int flowAll;

    private final static Logger logger = LoggerFactory.getLogger(HelloWorld.class);

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

        RateLimiter rateLimiter = RateLimiter.create(5);

        PropertyUtils.get("database.url", "/test.properties");

        rateLimiter.setRate(200);
        rateLimiter.tryAcquire();

        rateLimiter.acquire();

        Greeting greeting = new Greeting();
        //System.out.println(greeting.sayHello());

        System.out.println(dbUrl);

        //amsDbTestService.getAllAms();

        //mqDemoServiceImpl.receiveMessage("oms_name");


        //spring-data-redis set key value
//        redisTemplate.executePipelined(new RedisCallback<Object>() {
//            @Override
//            public Object doInRedis(RedisConnection connection) throws DataAccessException {
//                byte[] ret = connection.get(redisTemplate.getStringSerializer().serialize("user.uid." + 121232321));
//                return redisTemplate.getStringSerializer().deserialize(ret);
//            }
//
//
//            public Object doInRedis(RedisConnection connection) throws DataAccessException {
//
//                connection.set(redisTemplate.getStringSerializer().serialize("user.uid." + 121232321),
//                        redisTemplate.getStringSerializer().serialize("mort"));
//                return null;
//            }
//        });

        String ret = "" ;
        logger.info(" redis qps is :  {}" , redisQps.incrementAndGet());
        try {
            ret = redisTemplate.opsForValue().get("user.uid." + 121232321);
        }catch (Exception e){
            logger.info(" redisTemplate.opsForValue().get  exception  is : {} " , e);
        }finally {
            logger.info(" redis qps is :  {} , {} " , redisQps.decrementAndGet()  , ret );
        }


        StockDealJson tmp = new StockDealJson();
        tmp.setStockId("688218");
        tmp.setDealDay(Timestamp.valueOf("2020-10-20 00:00:00"));
        try {
            stockDealJsonMapper.insertSelective(tmp);
        }catch (DuplicateKeyException du){
            logger.error("dup", du);
        }


        StockDetail stockDetail = stockDetailMapper.selectByPrimaryKey(104469L);

        stockDealJsonMapper.selectByPrimaryKey(381549L);

        ret = JSONObject.toJSONString(stockDetail);

        //redisTemplate.opsForValue().set("user.uid.121232321" , "qwqwqwqwqw");

        //redisTemplate.opsForSet();

//      String ret = redisTemplate.execute(new RedisCallback<String>() {
//            @Override
//            public String doInRedis(RedisConnection connection) throws DataAccessException {
//                byte[] ret = connection.get(redisTemplate.getStringSerializer().serialize("user.uid." + 121232321));
//                return redisTemplate.getStringSerializer().deserialize(ret);
//            }
//        });
//
//
       return ret ;
    }


    public static void main(String[] args) {


        String str ="{4097:28, 1583:34, 1395:22, 4180:22, 1448:26, 683:22, 1589:26, 1588:22, 1267:22,10004851:28,1597:28,1596:28,822:22,757:26,869:26,1419:22,1449:26,3712:26,3702:26,3694:26,3683:26,1457:26,1456:26,1455:26,1454:26,1453:26,1452:26,1451:26,1450:26}" ;
        //Map<String ,String> categoryMap : (Map) JSON.parseArray(str);

        //JSONObject  jasonObject = JSONObject.parseObject(str);
        //Map map = (Map)jasonObject;
        //System.out.println(map.get(String.valueOf(4097)));

        final String shopCids = "#682# #1395# #724#";

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
