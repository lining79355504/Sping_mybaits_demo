package com.demo.api;

import com.demo.api.dto.SoaTestApiDto;
import com.demo.api.dto.SoaTestParamApiDto;
import org.springframework.stereotype.Service;

/**
 * @author mort
 * @Description
 * @date 2021/11/22
 **/
@Service
public class SoaTestApiServiceImpl implements SoaTestApiService{


    @Override
    public SoaTestApiDto getById(SoaTestParamApiDto paramApiDto) {
        return new SoaTestApiDto() ;
    }
}
