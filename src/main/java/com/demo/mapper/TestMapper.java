package com.demo.mapper;

import com.demo.entityTmp.Test;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Author:  lining17
 * Date :  2020-02-27
 */

public interface TestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Test record);

    int insertSelective(Test record);

    Test selectByPrimaryKey(Integer id);

    List<Test> findFirst20ByIdLessThan(@Param("maxId") Integer maxId);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);

    List<Test> selectByPage(@Param("id") Integer id, @Param("start") Integer start, @Param("pageSize") Integer pageSize);
}