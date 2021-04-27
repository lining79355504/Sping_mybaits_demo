package com.demo.mapper;

import com.demo.dao.StockDealJson;

/**
 * @Description 
 * @author mort
 * @date 2021/4/27
 **/
public interface StockDealJsonMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockDealJson record);

    int insertSelective(StockDealJson record);

    StockDealJson selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockDealJson record);

    int updateByPrimaryKey(StockDealJson record);
}