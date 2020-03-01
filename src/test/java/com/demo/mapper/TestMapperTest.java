package com.demo.mapper;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * Author:  lining17
 * Date :  2020-02-27
 */
public class TestMapperTest {
    private static TestMapper mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder =
                new SqlSessionFactoryBuilder().build(TestMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/TestMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(TestMapper.class, builder.openSession(true));
    }

    @Test
    public void testInsert() throws FileNotFoundException {
//        mapper.insert();
    }
}
