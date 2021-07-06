package com.demo.api;

import com.demo.api.dto.SoaTestApiDto;
import com.demo.api.dto.SoaTestParamApiDto;

/**
 * @author mort
 * @Description
 * @date 2021/7/6
 **/
public interface SoaTestApiService {

    SoaTestApiDto getById(SoaTestParamApiDto paramApiDto);

}
