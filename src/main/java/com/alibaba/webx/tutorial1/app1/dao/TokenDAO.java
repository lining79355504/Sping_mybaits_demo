package com.alibaba.webx.tutorial1.app1.dao;

import com.alibaba.webx.tutorial1.app1.vo.Token;
import com.alibaba.webx.tutorial1.app1.vo.TokenExample;
import java.util.List;

public interface TokenDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_token
     *
     * @mbggenerated Fri Aug 19 12:32:31 CST 2016
     */
    int countByExample(TokenExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_token
     *
     * @mbggenerated Fri Aug 19 12:32:31 CST 2016
     */
    int deleteByExample(TokenExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_token
     *
     * @mbggenerated Fri Aug 19 12:32:31 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_token
     *
     * @mbggenerated Fri Aug 19 12:32:31 CST 2016
     */
    void insert(Token record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_token
     *
     * @mbggenerated Fri Aug 19 12:32:31 CST 2016
     */
    void insertSelective(Token record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_token
     *
     * @mbggenerated Fri Aug 19 12:32:31 CST 2016
     */
    List<Token> selectByExample(TokenExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_token
     *
     * @mbggenerated Fri Aug 19 12:32:31 CST 2016
     */
    Token selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_token
     *
     * @mbggenerated Fri Aug 19 12:32:31 CST 2016
     */
    int updateByExampleSelective(Token record, TokenExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_token
     *
     * @mbggenerated Fri Aug 19 12:32:31 CST 2016
     */
    int updateByExample(Token record, TokenExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_token
     *
     * @mbggenerated Fri Aug 19 12:32:31 CST 2016
     */
    int updateByPrimaryKeySelective(Token record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_token
     *
     * @mbggenerated Fri Aug 19 12:32:31 CST 2016
     */
    int updateByPrimaryKey(Token record);
}