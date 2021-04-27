package com.demo.dao;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description 
 * @author mort
 * @date 2021/4/27
 **/
public class StockDealJson {
    private Long id;

    private String stockId;

    private Date dealDay;

    /**
    * jsonContent
    */
    private String content;

    private Integer addTime;

    private BigDecimal priceAD;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public Date getDealDay() {
        return dealDay;
    }

    public void setDealDay(Date dealDay) {
        this.dealDay = dealDay;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    public BigDecimal getPriceAD() {
        return priceAD;
    }

    public void setPriceAD(BigDecimal priceAD) {
        this.priceAD = priceAD;
    }
}