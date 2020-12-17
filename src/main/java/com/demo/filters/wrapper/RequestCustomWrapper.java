package com.demo.filters.wrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;

/**
 * @author mort
 * @Description
 * @date 2020/12/17
 **/
public class RequestCustomWrapper extends HttpServletRequestWrapper {

    private static final Logger logger = LoggerFactory.getLogger(RequestCustomWrapper.class);

    private Map<String, String[]> parameterMap;

    private String body;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request
     * @throws IllegalArgumentException if the request is null
     */
    public RequestCustomWrapper(HttpServletRequest request) {
        super(request);
        parameterMap = request.getParameterMap();

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (Exception ex) {
            logger.error("", ex);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        body = stringBuilder.toString();
    }

    @Override
    public Enumeration<String> getParameterNames() {
        Vector<String> vector = new Vector<String>(parameterMap.keySet());
        return vector.elements();
    }

    @Override
    public String[] getParameterValues(String name) {
        return parameterMap.get(name);
    }

    @Override
    public String getParameter(String name) {
        String[] results = parameterMap.get(name);
        if (null == results) {
            return null;
        }
        return results[0];
    }

}
