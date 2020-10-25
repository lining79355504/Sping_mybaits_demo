package com.demo.daoTmp;

import com.demo.entityTmp.MobileTagValue;

public interface MobileTagValueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileTagValue record);

    int insertSelective(MobileTagValue record);

    MobileTagValue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileTagValue record);

    int updateByPrimaryKey(MobileTagValue record);
}