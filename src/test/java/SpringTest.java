import com.demo.file.ExcelUtil;
import com.demo.inf.CacheInterface;
import com.demo.service.MqDemoService;
import com.demo.service.impl.AmsDbTestServiceImpl;
import com.demo.service.impl.MqDemoServiceImpl;
import com.demo.utils.CacheService;
import com.demo.utils.LockUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.PortableInterceptor.INACTIVE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by 2015-218-pc on 8/25/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-core.xml"})
public class SpringTest {


    private static final Logger logger = LoggerFactory.getLogger(SpringTest.class);

    @Autowired
    private MqDemoService mqDemoService ;

//    @Autowired
//    private LockUtils lockUtils;

    @Autowired
    private ExcelUtil excelUtil ;

    @Test
    public void testSpringInit(){

        System.out.println(mqDemoService.getDbUrl());
        Assert.assertTrue(mqDemoService.getDbUrl() != null && "jdbc:mysql://172.19.30.22/ams".equals(mqDemoService.getDbUrl()));
    }



    @Test
    public void lockTest(){

//        lockUtils.getLock("174874816" , 2,3);


    }


    @Test
    public void getTest(){

        excelUtil.excelHandler();
    }


    @Test
    public void postTest(){


        try {
            excelUtil.httpPostWithJSON("http://localhost:8080/oss/point/ajax/pointReissueAjax");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void cookerSetTest(){
        excelUtil.cookerSet();
    }

    @Test
    public void caCheServiceTest(){


        CacheService cacheService = new CacheService<Integer , Integer>();

        Integer ret = (Integer) cacheService.get(2, 3, new CacheInterface() {
            @Override
            public HashMap cache(final Object key) {
                return new HashMap(){
                    {
                        put(key,11);
                    }
                };
            }
        });

        logger.info("ret is {}" , ret );

        try {
//            Thread.sleep(2*1000);
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ret = (Integer) cacheService.get(2, 3, new CacheInterface<Integer,Integer>() {
            @Override
            public HashMap cache(final Integer key) {
                return new HashMap(){
                    {
                        String value = excelUtil.retTest(Integer.valueOf(22));
                        logger.info("change value  {}",value);
                        put(key,Integer.valueOf(value));
                    }
                };
            }
        });

        logger.info("ret is {}" , ret );

    }


}
