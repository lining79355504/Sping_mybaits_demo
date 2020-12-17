package com.demo.filters;

import com.google.common.base.Charsets;
import org.bson.io.OutputBuffer;
import org.springframework.util.ReflectionUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Enumeration;

/**
 * @author mort
 * @Description
 * @date 2020/12/16
 **/
public class ReflectGetResponseFilter implements Filter  {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {



        String path = ((HttpServletRequest) request).getRequestURI();

        Enumeration<String> parameterNames = request.getParameterNames();

        chain.doFilter(request, response);

        //forward 转发  也可用httpClient 转发
        //request.getRequestDispatcher("http://blog.csdn.net/goskalrie").forward(req, res);//HTTP

//        request.getRequestDispatcher("/").forward(request, response);
//
//        // 截取响应流
//        CoyoteOutputStream os = (CoyoteOutputStream) response.getOutputStream();
//        // 取到流对象对应的Class对象
//        Class<CoyoteOutputStream> c = CoyoteOutputStream.class;
//        // 取出流对象中的OutputBuffer对象，该对象记录响应到客户端的内容
//        Field fs = c.getDeclaredField("ob");
//        if (fs.getType().toString().endsWith("OutputBuffer")) {
//            fs.setAccessible(true);// 设置访问ob属性的权限
//            OutputBuffer ob = (OutputBuffer) fs.get(os);// 取出ob
//            Class<OutputBuffer> cc = OutputBuffer.class;
//            Field ff = cc.getDeclaredField("outputChunk");// 取到OutputBuffer中的输出流
//            ff.setAccessible(true);
//            if (ff.getType().toString().endsWith("ByteChunk")) {
//                ByteChunk bc = (ByteChunk) ff.get(ob);// 取到byte流
//
//                String val = new String(bc.getBytes(), "UTF-8");// 最终的值
//                System.out.println(val);
//            }
//        }



        System.out.println("request = " + request + ", response = " + response + ", chain = " + chain);

        ServletOutputStream os = response.getOutputStream();
        Field ob= ReflectionUtils.findField(os.getClass(),"ob");
        ob.setAccessible(true);
        Object obValue= ReflectionUtils.getField(ob,os);
        Field bb= ReflectionUtils.findField(obValue.getClass(),"bb");
        bb.setAccessible(true);
        Object bbValue= ReflectionUtils.getField(bb,obValue);
        Field hb= ReflectionUtils.findField(bbValue.getClass(),"hb");
        hb.setAccessible(true);
        os.flush();
        Object value= ReflectionUtils.getField(hb,bbValue);
        String s = new String((byte[]) value, Charsets.UTF_8);
        System.out.println("request = " + s);


    }

    @Override
    public void destroy() {

    }
}
