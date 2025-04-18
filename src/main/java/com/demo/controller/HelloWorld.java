package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.annotataion.MyAnnotation;
import com.demo.api.SoaTestApiService;
import com.demo.api.dto.SoaTestParamApiDto;
import com.demo.dao.StockDealJson;
import com.demo.dao.StockDetail;
import com.demo.mapper.StockDealJsonMapper;
import com.demo.mapper.StockDetailMapper;
import com.demo.ratelimit.GuavaRateLimiterUtil;
import com.demo.service.MqDemoService;
import com.demo.service.impl.AmsDbTestServiceImpl;
import com.demo.springCore.annotation.Limit;
import com.demo.springCore.resource.MyBeanAwareByAnnotation;
import com.demo.utils.PropertyUtils;
import com.google.common.util.concurrent.RateLimiter;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 2015-218-pc on 8/18/16.
 */

@Controller
public class HelloWorld {

    @Autowired
    private MqDemoService mqDemoServiceImpl;

    @Autowired
    private AmsDbTestServiceImpl amsDbTestService;

    @Autowired
    private SoaTestApiService soaTestApiService;


    @Autowired
    protected RedisTemplate<String, String> redisTemplate;

    @Autowired
    private StockDetailMapper stockDetailMapper;

    @Autowired
    private StockDealJsonMapper stockDealJsonMapper;

    @Autowired
    private MyBeanAwareByAnnotation myBeanAwareByAnnotation;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //可以主从分离 配置从库数据源 或者主从分离数据源 实现不新建dao和mapper的编程式主从分离
    @Resource
    @Qualifier("sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    private AtomicInteger redisQps = new AtomicInteger();

    //引入配置
    @Value("#{mortTestConfigs['database.url']}")
    private String dbUrl;

    @Value("#{flowControl['flow.all']}")
    private int flowAll;

    private final static Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    private static RateLimiter limiter;

    static {
        limiter = GuavaRateLimiterUtil.createResourceRateLimiter("test", 1);
    }

    /*
        @RequestParam Map<String, String> params   get post 都可已接收 全部的参数转为一个map
    *    public void export(@RequestParam Map<String, String> params ,HttpServletResponse response){
    *
    *    }
    * */
    @ResponseBody
    @RequestMapping("/hello")
    @MyAnnotation(value = "hello")
    @Limit(name = "hello", qps = 3)
    public String index(HttpServletRequest req, HttpServletResponse res) {

        try {

            SoaTestParamApiDto paramApiDto = new SoaTestParamApiDto();
            paramApiDto.setId(0);
            stockDealJsonMapper.selectByPrimaryKey(381549L);

            soaTestApiService.getById(paramApiDto);
            String ret = "";

            mqDemoServiceImpl.getDbUrl();

            RateLimiter rateLimiter = limiter;

            PropertyUtils.get("database.url", "/test.properties");

            rateLimiter.setRate(200);
            rateLimiter.tryAcquire();

            rateLimiter.acquire();

            Greeting greeting = new Greeting();
            //System.out.println(greeting.sayHello());

            System.out.println(dbUrl);

            //amsDbTestService.getAllAms();

            //mqDemoServiceImpl.receiveMessage("oms_name");


            jdbcTemplate.execute("select 1 ");

            sqlSessionTemplate.selectOne("com.demo.mapper.PointApplyMapper.selectByPrimaryKey", 12);

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

            logger.info(" redis qps is :  {}", redisQps.incrementAndGet());
            try {
                ret = redisTemplate.opsForValue().get("user.uid." + 121232321);
            } catch (Exception e) {
                logger.info(" redisTemplate.opsForValue().get  exception  is : {} ", e);
            } finally {
                logger.info(" redis qps is :  {} , {} ", redisQps.decrementAndGet(), ret);
            }


            StockDealJson tmp = new StockDealJson();
            tmp.setStockId("688218");
            tmp.setDealDay(Timestamp.valueOf("2020-10-20 00:00:00"));
            try {
                stockDealJsonMapper.insertSelective(tmp);
            } catch (DuplicateKeyException du) {
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
            return "success";
        } catch (Exception e) {
            logger.error("", e);
            return "error";
        }
    }


    public static void main(String[] args) {


        String str = "{4097:28, 1583:34, 1395:22, 4180:22, 1448:26, 683:22, 1589:26, 1588:22, 1267:22,10004851:28,1597:28,1596:28,822:22,757:26,869:26,1419:22,1449:26,3712:26,3702:26,3694:26,3683:26,1457:26,1456:26,1455:26,1454:26,1453:26,1452:26,1451:26,1450:26}";
        //Map<String ,String> categoryMap : (Map) JSON.parseArray(str);

        //JSONObject  jasonObject = JSONObject.parseObject(str);
        //Map map = (Map)jasonObject;
        //System.out.println(map.get(String.valueOf(4097)));

        final String shopCids = "#682# #1395# #724#";

        String shopCid[] = shopCids.split("#");
        JSONObject jasonObject = JSONObject.parseObject(str);
        Map categoryMap = (Map) jasonObject;
        System.out.println(categoryMap.get("4097").getClass());

        List aList = new ArrayList();
        aList.add(22991416655268L);
        aList.add(22991416655269L);

        System.out.println(aList);

        List<String> sList = (List<String>) aList;


        System.out.println(sList.get(0).getClass().toString());


        //装箱 Integer 缓存 反射
        Class cache = Integer.class.getDeclaredClasses()[0];
        Field c = null;
        try {
            c = cache.getDeclaredField("cache");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        c.setAccessible(true);

        Integer[] array = new Integer[0];
        try {
            array = (Integer[]) c.get(cache);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
// array[129] is 1
        array[130] = array[129];
// Set 2 to be 1
        array[131] = array[129];
// Set 3 to be 1
//        Integer a = new Integer(1);
        Integer a = 1;
        if (a == (Integer) 1 && a == (Integer) 2 && a == (Integer) 3) {
            System.out.println("Success");
        } else {
            System.out.println("no");
        }
    }


}
