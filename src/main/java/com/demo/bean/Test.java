package com.demo.bean;

import org.elasticsearch.cluster.routing.Murmur3HashFunction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by mort on 01/08/2017.
 */
public class Test {


    public static void main(String[] args) {

        //redis 也使用改算法计算key 优点是无论key是什么样的 结果都很随机 均衡
        //es使用该算法 计算shradId   hash(_id)%shrads   例如以下10即为10个shard
        int i = Murmur3HashFunction.hash("567032019") % 10;

        for (int j = 567032019; j < 567032019 + 10000000; j++) {
            if(8 ==  Math.floorMod(Murmur3HashFunction.hash(String.valueOf(j)), 10)){
                System.out.println(j);
            }
        }

        ApplicationContext context = new ClassPathXmlApplicationContext(
                "spring-core.xml");

        UserEntity user = (UserEntity) context.getBean("user");
        System.out.println(user.getName());
        System.out.println(user.getAge());
        System.out.println(new UserEntity().getAge());


    }
}
