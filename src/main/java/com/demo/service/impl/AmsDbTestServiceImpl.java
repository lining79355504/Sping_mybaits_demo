package com.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 2015-218-pc on 8/23/16.
 */
@Service("amsDbTestService")
public class AmsDbTestServiceImpl {


//    @Autowired
//    private TokenMapper tokenMapper;

    @Transactional()
    public void getAllAms(){

        //TokenDAOImpl tokenDAOImpl = new TokenDAOImpl();

//        Token token = tokenMapper.selectByPrimaryKey( 11L);

//        System.out.println(token.getDealTime());
        //System.out.println(token.toString());

    }


    public static void main(String[] args) {
        //System.out.println(Size.);

        Object on ;

        String str = "12121";

       // Method method = str.getClass().getMethod();
    }

}
