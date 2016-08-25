import com.demo.service.MqDemoService;
import com.demo.service.impl.AmsDbTestServiceImpl;
import com.demo.service.impl.MqDemoServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by 2015-218-pc on 8/25/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-core.xml"})
@ActiveProfiles("pre")
public class SpringTest {


    @Autowired
    private MqDemoService mqDemoService ;

    @Test
    public void testSpringInit(){

        System.out.println(System.getProperties());
        System.out.println(mqDemoService.getDbUrl());
        Assert.assertTrue(mqDemoService.getDbUrl() != null && "abc".equals(mqDemoService.getDbUrl()));
    }
}
