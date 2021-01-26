package com.demo.utils;

import com.demo.test.entity.AccAccountWalletLogPoEntity;
import com.demo.test.entity.CanalBinlogEntity;
import com.demo.utils.annotation.SerializeFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
遇到时间格式化异常 使用 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
*
* */


public class JacksonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JacksonUtils.class);

    public final static ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.setAnnotationIntrospector(new CustomAnnotationSerializer());
    }

    public static String serialize(Object obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("serialize error ", e);
        }
        return null;
    }

    public static <T> T deserialize(String string, Class<T> classOfT) {
        try {
            return MAPPER.readValue(string, classOfT);
        } catch (Exception e) {
            logger.error("deserialize error ", e);
        }
        return null;
    }

    public static <T> T deserialize(String string, TypeReference valueTypeRef) {
        try {
            return MAPPER.readValue(string, valueTypeRef);
        } catch (Exception e) {
            logger.error("deserialize error ", e);
        }
        return null;
    }


    /**
     * // 自定义注解支持
     * spring 默认jackson配置支持  spring默认序列化配置
     * <p>
     * <mvc:annotation-driven>
     * <mvc:message-converters>
     * <ref bean="stringHttpMessageConverter"/>
     * <ref bean="mappingJacksonHttpMessageConverter"/>
     * </mvc:message-converters>
     * </mvc:annotation-driven>
     * <p>
     * <p>
     * <bean id="stringHttpMessageConverter"
     * class="org.springframework.http.converter.StringHttpMessageConverter"/>
     * <p>
     * <bean id="mappingJacksonHttpMessageConverter"
     * class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
     * <property name="supportedMediaTypes">
     * <list>
     * <value>application/json;charset=UTF-8</value>
     * <value>text/html;charset=UTF-8</value>
     * </list>
     * </property>
     * <property name="objectMapper">
     * <bean class="com.bilibili.adp.advertiser.portal.openapi.valid.serialize.CustomMapper"/>
     * </property>
     * </bean>
     *
     * <bean class="com.bilibili.adp.advertiser.portal.openapi.valid.serialize.OpenAPICustomSerializer"/>
     *
     * <bean class=" org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter">
     * <property name="messageConverters">
     * <list>
     * <ref bean="mappingJacksonHttpMessageConverter"/>
     * </list>
     * </property>
     * </bean>
     */

    public static class CustomAnnotationSerializer extends JacksonAnnotationIntrospector {

        @Override
        public boolean hasIgnoreMarker(AnnotatedMember m) {
            SerializeFilter annotation = m.getAnnotation(SerializeFilter.class);
            if (null != annotation) {
                return true;
            } else {
                return super.hasIgnoreMarker(m);
            }
        }
    }

}
