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
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HttpUtils {

    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    public static String doGet(Map<String, String> params, String url, boolean isSplit) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            CloseableHttpResponse response;
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
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

    public static String doPost(Map<String, Object> params, String url) {
//        CloseableHttpClient client = HttpClients.custom().setConnectionManager().build();
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            CloseableHttpResponse response;
            HttpPost httpPost = new HttpPost(url);
            if (params != null) {
                httpPost.setHeader("Content-Type", "application/json");
                String body = new Gson().toJson(params);
                httpPost.setEntity(new StringEntity(body));
            }
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }


}
