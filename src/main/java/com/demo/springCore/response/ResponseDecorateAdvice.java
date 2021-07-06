package com.demo.springCore.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author mort
 * @Description
 * @date 2020/12/17
 **/
@ControllerAdvice
public class ResponseDecorateAdvice implements ResponseBodyAdvice {


    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setAnnotationIntrospector(new OpenAPICustomSerializer());
    }


    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        String path = serverHttpRequest.getURI().getPath();
        serverHttpRequest.getHeaders().get("");// 获取Header  attribute
        return body;
    }
}
