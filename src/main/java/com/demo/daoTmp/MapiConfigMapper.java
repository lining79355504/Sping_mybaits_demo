package com.demo.daoTmp;

import com.demo.entityTmp.MapiConfig;

public interface MapiConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MapiConfig record);

    int insertSelective(MapiConfig record);

    MapiConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MapiConfig record);

    int updateByPrimaryKey(MapiConfig record);
}