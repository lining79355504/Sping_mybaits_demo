package com.demo.service.impl;

import com.demo.dao.Token;
import com.demo.mapper.TokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 2015-218-pc on 8/23/16.
 */
@Service("amsDbTestService")
public class AmsDbTestServiceImpl {


    @Autowired
    private TokenMapper tokenMapper;

    @Transactional()
    public void getAllAms(){

        //TokenDAOImpl tokenDAOImpl = new TokenDAOImpl();

        Token token = tokenMapper.selectByPrimaryKey( Integer.valueOf(11) );

        //System.out.println(token.getToken());
        System.out.println(token.toString());

    }

}
