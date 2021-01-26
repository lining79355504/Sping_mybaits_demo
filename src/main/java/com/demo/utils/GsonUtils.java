package com.demo.utils;

import com.demo.utils.annotation.SerializeFilter;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:  lining17
 * Date :  2019-07-26
 */

/*
* 防止gson 序列化科学计数法 int 变double 等
*
*
* */
public class GsonUtils {

    private static Gson gson =
            new GsonBuilder()
                    .registerTypeAdapter(new TypeToken<Map<String, Object>>() {}.getType(), new GsonTypeAdapter())
                    .setFieldNamingStrategy(new NamingStrategy())
                    .setExclusionStrategies(new ExclusionStrategy() {
                        @Override
                        public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                            //自定义注解跳过字段
                            SerializeFilter annotation = fieldAttributes.getAnnotation(SerializeFilter.class);
                            return null != annotation;
                        }

                        @Override
                        public boolean shouldSkipClass(Class<?> aClass) {
                            return false;
                        }
                    })
                    .create();

    public static JsonObject jsonParse(String jsonStr) {
        return gson.fromJson(jsonStr, JsonObject.class);
    }

    public static <T> T fromJson(String json, Class<T> tClass) {
        return gson.fromJson(json, tClass);
    }

    public static String toJson(Object object) {
        return gson.toJson(object);
    }


    public static class GsonTypeAdapter extends TypeAdapter<Object> {
        @Override
        public Object read(JsonReader in) throws IOException {
            // 反序列化
            JsonToken token = in.peek();
            switch (token) {
                case BEGIN_ARRAY:
                    List<Object> list = new ArrayList<Object>();
                    in.beginArray();
                    while (in.hasNext()) {
                        list.add(read(in));
                    }
                    in.endArray();
                    return list;
                case BEGIN_OBJECT:
                    Map<String, Object> map = new HashMap<String, Object>();
                    in.beginObject();
                    while (in.hasNext()) {
                        map.put(in.nextName(), read(in));
                    }
                    in.endObject();
                    return map;
                case STRING:
                    return in.nextString();
                case NUMBER:
                    double dbNum = in.nextDouble();

                    if (dbNum > Long.MAX_VALUE) {
                        return dbNum;
                    }

                    long lngNum = (long) dbNum;
                    if (dbNum == lngNum) {
                        return lngNum;
                    } else {
                        return dbNum;
                    }

                case BOOLEAN:
                    return in.nextBoolean();
                case NULL:
                    in.nextNull();
                    return null;
                default:
                    throw new IllegalStateException();
            }
        }

        @Override
        public void write(JsonWriter out, Object value) throws IOException {
        }
    }

    // gson 根据实际的filed name序列反序列化，
    // 和fastJson  jackson 序列化字段策略不一致，
    // fastJson和jackSon 根据get is方法序列化 （Java bean doc官方文档推荐的 get set方法）基本boolean是默认is 方法
    private static class NamingStrategy implements FieldNamingStrategy {
        @Override
        public String translateName(Field f) {
            String name = f.getName();
            if (name.startsWith("m_")) {
                return name.substring(2, name.length());
            } else {
                return name;
            }
        }
    }


}
