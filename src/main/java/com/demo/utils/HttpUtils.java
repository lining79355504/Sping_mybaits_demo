package com.demo.utils;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HttpUtils {

    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    public static HttpEntity doGet(Map<String, String> params, String url, boolean isSplit) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response;
        HttpEntity entity = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            List<NameValuePair> list = new LinkedList<>();
            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    if (isSplit && value.contains(",")) {
                        String[] values = value.split(",");
                        for (String v : values) {
                            list.add(new BasicNameValuePair(entry.getKey(), v));
                        }
                    } else {
                        list.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
            }
            uriBuilder.setParameters(list);

            HttpGet httpGet = new HttpGet(uriBuilder.build());
            response = httpClient.execute(httpGet);
            entity = response.getEntity();

            return entity;
        } catch (Exception e) {
            log.error("",e);
        }

        return entity;
    }

    public static HttpEntity doPost(Map<String, Object> params, String url) {
        HttpEntity entity = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            CloseableHttpResponse response;
            HttpPost httpPost = new HttpPost(url);
            if (params != null) {
                httpPost.setHeader("Content-Type", "application/json");
                String body = new Gson().toJson(params);
                httpPost.setEntity(new StringEntity(body));
            }
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
//            EntityUtils.toString(entity);
            return entity;
        } catch (Exception e) {
            log.error("",e);
        }
        return entity;
    }


}
