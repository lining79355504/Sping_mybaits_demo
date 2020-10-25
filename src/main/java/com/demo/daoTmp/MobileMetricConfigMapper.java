package com.demo.daoTmp;

import com.demo.entityTmp.MobileMetricConfig;

public interface MobileMetricConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileMetricConfig record);

    int insertSelective(MobileMetricConfig record);

    MobileMetricConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileMetricConfig record);

    int updateByPrimaryKeyWithBLOBs(MobileMetricConfig record);

    int updateByPrimaryKey(MobileMetricConfig record);
}