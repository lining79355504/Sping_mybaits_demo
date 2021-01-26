package com.demo.filters;

import com.demo.filters.wrapper.ResponseCustomWrapper;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author mort
 * @Description
 * @date 2020/12/16
 * 可以设置返回值统一加密 等
 **/
@Component("responseChangeFilter")
public class ResponseChangeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        ResponseCustomWrapper wrapperResponse = new ResponseCustomWrapper((HttpServletResponse) response);//转换成代理类

        String requestURI = ((HttpServletRequest) request).getRequestURI();

        String requestStr = getRequestStr((HttpServletRequest) request);


        //转发
//        request.getRequestDispatcher("/").forward(request, response);


        chain.doFilter(request, wrapperResponse);
        byte[] content = wrapperResponse.getContent();//获取返回值

        if (content.length > 0) {

            String str = new String(content, "UTF-8");
            System.out.println("返回值:" + str);
            String ciphertext = "返回值:" + str;

            try {
                //......根据需要处理返回值
            } catch (Exception e) {
                e.printStackTrace();
            }
            //把返回值输出到客户端
            ServletOutputStream out = response.getOutputStream();
            byte[] bytes = ciphertext.getBytes("UTF-8");
            response.setContentLength(bytes.length); //注意顺序必须在 write之前
            out.write(bytes);
            out.flush();
        }

    }

    @Override
    public void destroy() {

    }



    /***
     * Get request query string
     * @param request
     * @return   byte[]
     */
    public byte[] getRequestBytes(HttpServletRequest request){
        int contentLength = request.getContentLength();
        if(contentLength <= 0){
            return new byte[1];
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength;) {
            try {

                int readlen = request.getInputStream().read(buffer, i,
                        contentLength - i);
                if (readlen == -1) {
                    break;
                }
                i += readlen;
            } catch (IOException ioexception) {
                ioexception.printStackTrace();
            } finally {
                // logger.info("Json Request:" + requestPacket);
            }
        }
        return buffer;
    }


    /***
     * Get request query string
     *
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getRequestStr(HttpServletRequest request) throws UnsupportedEncodingException {
        byte buffer[]=getRequestBytes(request);
        String charEncoding=request.getCharacterEncoding();
        if(charEncoding==null){
            charEncoding="UTF-8";
        }
        return new String(buffer,charEncoding);
    }
}
