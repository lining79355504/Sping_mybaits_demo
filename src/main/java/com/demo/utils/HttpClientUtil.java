package com.demo.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

@Service
public class HttpClientUtil implements InitializingBean {

	private RequestConfig m_requestConfig = RequestConfig.custom().setConnectTimeout(3000)
	      .setConnectionRequestTimeout(2000).setSocketTimeout(5000).build();

	private CloseableHttpClient m_httpClient;

	public String executeGet(String url) {
		String path = null;

		try {
			URL u = new URL(url);
			path = u.getPath();
		} catch (MalformedURLException e1) {
			path = url;
		}

		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String result = "";

		try {
			HttpGet httpGet = new HttpGet(url);
			httpGet.setConfig(m_requestConfig);

			response = m_httpClient.execute(httpGet);
			entity = response.getEntity();

			if (entity != null) {
				InputStream is = entity.getContent();
				Scanner s = new Scanner(is).useDelimiter("\\A");
				result = s.hasNext() ? s.next() : "";
			}

		} catch (Exception e) {

		} finally {
			try {
				EntityUtils.consume(entity);

				if (response != null) {
					response.close();
				}
			} catch (Exception ex) {

			}

		}
		return result;
	}

	public String sendPostJson(String sendurl, String data, Map<String, String> headers) {
		HttpPost post = new HttpPost(sendurl);
		StringEntity myEntity = new StringEntity(data, ContentType.APPLICATION_JSON);// 构造请求数据
		post.setEntity(myEntity);// 设置请求体
		for (Map.Entry<String, String> headerEntry : headers.entrySet()) {
			post.setHeader(headerEntry.getKey(), headerEntry.getValue());
		}
		String responseContent = null; // 响应内容
		CloseableHttpResponse response = null;


		try {
			response = m_httpClient.execute(post);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				responseContent = EntityUtils.toString(entity, "UTF-8");

			} else {

			}

		} catch (Exception e) {

		} finally {
			try {
				if (response != null)
					response.close();
			} catch (Exception e) {

			}

		}
		return responseContent;
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(200);
		cm.setDefaultMaxPerRoute(50);
		m_httpClient = HttpClients.custom().setConnectionManager(cm).build();

	}
}
