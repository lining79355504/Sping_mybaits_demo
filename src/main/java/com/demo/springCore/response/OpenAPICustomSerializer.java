package com.demo.springCore.response;

import com.demo.utils.annotation.SerializeFilter;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * @author mort
 * @Description
 * @date 2021/1/26
 **/
public class OpenAPICustomSerializer extends JacksonAnnotationIntrospector {

    private static final String HEADER_KEY = "xx";

    @Override
    public boolean hasIgnoreMarker(AnnotatedMember m) {

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (null == requestAttributes) {
            return super.hasIgnoreMarker(m);
        }

        HttpServletRequest request = requestAttributes.getRequest();
        SerializeFilter annotation = m.getAnnotation(SerializeFilter.class);
        if (null != request.getHeader(HEADER_KEY) && null != annotation) {
            return true;
        } else {
            return super.hasIgnoreMarker(m);
        }
    }
}
