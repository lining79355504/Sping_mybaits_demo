package com.demo.mapper;

import com.demo.dao.Token;
import org.springframework.stereotype.Service;

//@DataSourceRouting("ams")
//@Service
public interface TokenMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_token
     *
     * @mbggenerated Thu Aug 25 12:37:38 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_token
     *
     * @mbggenerated Thu Aug 25 12:37:38 CST 2016
     */
    int insert(Token record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_token
     *
     * @mbggenerated Thu Aug 25 12:37:38 CST 2016
     */
    int insertSelective(Token record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_token
     *
     * @mbggenerated Thu Aug 25 12:37:38 CST 2016
     */
    Token selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_token
     *
     * @mbggenerated Thu Aug 25 12:37:38 CST 2016
     */
    int updateByPrimaryKeySelective(Token record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_token
     *
     * @mbggenerated Thu Aug 25 12:37:38 CST 2016
     */
    int updateByPrimaryKey(Token record);
}