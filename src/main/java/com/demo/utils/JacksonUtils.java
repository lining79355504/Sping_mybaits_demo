package com.demo.utils;

import com.demo.test.entity.AccAccountWalletLogPoEntity;
import com.demo.test.entity.CanalBinlogEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public static void main(String[] args) {

        String str = "{\n" +
                "\t\"data\": [{\n" +
                "\t\t\"id\": \"328282268\",\n" +
                "\t\t\"account_id\": \"362333\",\n" +
                "\t\t\"operation_type\": \"0\",\n" +
                "\t\t\"cash\": \"0\",\n" +
                "\t\t\"remark\": \"\",\n" +
                "\t\t\"is_deleted\": \"0\",\n" +
                "\t\t\"ctime\": \"2020-11-03 21:29:34\",\n" +
                "\t\t\"mtime\": \"2020-11-03 21:29:34\",\n" +
                "\t\t\"red_packet\": \"101\",\n" +
                "\t\t\"date\": \"2020-11-03\",\n" +
                "\t\t\"agent_id\": \"1985586\",\n" +
                "\t\t\"sales_type\": \"12\",\n" +
                "\t\t\"old_agent_id\": \"0\",\n" +
                "\t\t\"special_red_packet\": \"0\"\n" +
                "\t}],\n" +
                "\t\"database\": \"business_ad\",\n" +
                "\t\"es\": 1604410174000,\n" +
                "\t\"id\": 29691,\n" +
                "\t\"isDdl\": false,\n" +
                "\t\"mysqlType\": {\n" +
                "\t\t\"id\": \"bigint(20) unsigned\",\n" +
                "\t\t\"account_id\": \"int(11)\",\n" +
                "\t\t\"operation_type\": \"tinyint(4)\",\n" +
                "\t\t\"cash\": \"bigint(20)\",\n" +
                "\t\t\"remark\": \"varchar(128)\",\n" +
                "\t\t\"is_deleted\": \"tinyint(4)\",\n" +
                "\t\t\"ctime\": \"datetime\",\n" +
                "\t\t\"mtime\": \"datetime\",\n" +
                "\t\t\"red_packet\": \"bigint(20)\",\n" +
                "\t\t\"date\": \"date\",\n" +
                "\t\t\"agent_id\": \"int(11)\",\n" +
                "\t\t\"sales_type\": \"tinyint(4) unsigned\",\n" +
                "\t\t\"old_agent_id\": \"int(11) unsigned\",\n" +
                "\t\t\"special_red_packet\": \"bigint(20) unsigned\"\n" +
                "\t},\n" +
                "\t\"old\": null,\n" +
                "\t\"pkNames\": [\"id\"],\n" +
                "\t\"sql\": \"\",\n" +
                "\t\"sqlType\": {\n" +
                "\t\t\"id\": -5,\n" +
                "\t\t\"account_id\": 4,\n" +
                "\t\t\"operation_type\": -6,\n" +
                "\t\t\"cash\": -5,\n" +
                "\t\t\"remark\": 12,\n" +
                "\t\t\"is_deleted\": -6,\n" +
                "\t\t\"ctime\": 93,\n" +
                "\t\t\"mtime\": 93,\n" +
                "\t\t\"red_packet\": -5,\n" +
                "\t\t\"date\": 91,\n" +
                "\t\t\"agent_id\": 4,\n" +
                "\t\t\"sales_type\": -6,\n" +
                "\t\t\"old_agent_id\": 4,\n" +
                "\t\t\"special_red_packet\": -5\n" +
                "\t},\n" +
                "\t\"table\": \"acc_account_wallet_log\",\n" +
                "\t\"ts\": 1604410174079,\n" +
                "\t\"type\": \"INSERT\"\n" +
                "}";

        CanalBinlogEntity<AccAccountWalletLogPoEntity> ret = deserialize(str, new TypeReference<CanalBinlogEntity<AccAccountWalletLogPoEntity>>() {
        });

    }
}
