import com.alibaba.fastjson.JSON;
import com.demo.file.ExcelUtil;
import com.demo.inf.CacheInterface;
import com.demo.service.MqDemoService;
import com.demo.utils.CacheService;
import com.demo.utils.CsvUtils;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

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

        Map  map = new HashMap();
        System.out.printf(JSON.toJSONString(map.get("adasd")));

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


    @Test
    public void scvTest(){

        CsvUtils.write();

    }


    public static void main(String[] args){
//        List<DishStoryAuditResultDTO> list = new ArrayList<>();
//        DishStoryAuditResultDTO dishStoryAuditResultDTO = new DishStoryAuditResultDTO();
//        dishStoryAuditResultDTO.setDishStoryPerfectionDetailId(54583);
//        dishStoryAuditResultDTO.setDishStoryPerfectionRecordId(17155);
//        dishStoryAuditResultDTO.setWhetherPass(true);
//        list.add(dishStoryAuditResultDTO);
//        JacksonSerializer serializer = new JacksonSerializer();
//        System.out.println(serializer.serializeObject(list));


//        List<DishStoryAuditResultDTO> list = new ArrayList<>();
//        DishStoryAuditResultDTO dishStoryAuditResultDTO = new DishStoryAuditResultDTO();
//        dishStoryAuditResultDTO.setDishStoryPerfectionDetailId(54583);
//        dishStoryAuditResultDTO.setDishStoryPerfectionRecordId(17155);
//        dishStoryAuditResultDTO.setWhetherPass(true);
//        list.add(dishStoryAuditResultDTO);
//        System.out.println(com.dianping.pigeon.remoting.common.codec.json.JacksonUtils.serialize(list));
//        System.out.println(com.dianping.pigeon.remoting.common.codec.json.JacksonUtils.serialize("lining17"));


    }


    @Test
    public void jsonDiffTest(){


        String strJson ="{\"map\":{\"a\":123.12312}}";

        TestDto testDto = new TestDto();

        //fastJson 可以在反序列化的时候自动将类型映射强转
        TestDto testDtoStr = JSON.parseObject(strJson, TestDto.class); //如果是内嵌类 这样用必须申明为static

        Gson gson = new Gson();
        //Gson 则不会吧double转为long 会抛出异常
        gson.fromJson(strJson,TestDto.class);



    }

    public static class TestDto{


        Map<String,Long> map = new HashMap<String,Long>();


        public Map<String, Long> getMap() {
            return map;
        }

        public void setMap(Map<String, Long> map) {
            this.map = map;
        }


    }


}
