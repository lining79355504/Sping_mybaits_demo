package com.demo.api.dto;

import java.io.Serializable;

/**
 * @author mort
 * @Description
 * @date 2021/7/6
 **/
public class SoaTestParamApiDto implements Serializable {


    private static final long serialVersionUID = -7200846472607788394L;

    private Integer id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
