package com.demo.utils;

import com.alibaba.fastjson.JSON;

import javax.activation.MimetypesFileTypeMap;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * @author mort
 * @Description
 * @date 2021/4/15
 *
 *
 * multipart/form-data  适合文件上传
 **/
public class FileUpload {


    public static String formUpload(String urlStr, Map<String, String> params, Map<String, String> fileMap) {
        String res = "";
        HttpURLConnection conn = null;
        // boundary就是request头和上传文件内容的分隔符
        String BOUNDARY = "---------------------------123821742118716";
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setRequestProperty("opxxheader", "test");
//            conn.setRequestProperty("Accept-Encoding", "gzip");
            conn.setRequestProperty("Content-Type","application/json;charset=UTF-8");
//            conn.setRequestProperty("Accept", "*/*");
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            conn.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + BOUNDARY);
            OutputStream out = new DataOutputStream(conn.getOutputStream());
            // formdata 参数填充
            if (params != null) {
                StringBuffer strBuf = new StringBuffer();
                Iterator iter = params.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String inputName = (String) entry.getKey();
                    String inputValue = (String) entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    strBuf.append("\r\n").append("--").append(BOUNDARY)
                            .append("\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\""
                            + inputName + "\"\r\n\r\n");
                    strBuf.append(inputValue);
                    System.out.println(inputName+","+inputValue);
                }
                out.write(strBuf.toString().getBytes());
            }
            // 文件填充
            if (fileMap != null) {
                Iterator iter = fileMap.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String inputName = (String) entry.getKey();
                    String inputValue = (String) entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    File file = new File(inputValue);
                    String filename = file.getName();
                    String contentType ;
                    //没有传入文件类型，同时根据文件获取不到类型，默认采用application/octet-stream
                    contentType = new MimetypesFileTypeMap().getContentType(file);
                    //contentType非空采用filename匹配默认的图片类型
                    if(!"".equals(contentType)){
                        if (filename.endsWith(".png")) {
                            contentType = "image/png";
                        }else if (filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".jpe")) {
                            contentType = "image/jpeg";
                        }else if (filename.endsWith(".gif")) {
                            contentType = "image/gif";
                        }else if (filename.endsWith(".ico")) {
                            contentType = "image/image/x-icon";
                        }
                    }
                    if (contentType == null || "".equals(contentType)) {
                        contentType = "application/octet-stream";
                    }
                    StringBuffer strBuf = new StringBuffer();
                    strBuf.append("\r\n").append("--").append(BOUNDARY)
                            .append("\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\""
                            + inputName + "\"; filename=\"" + filename
                            + "\"\r\n");
                    System.out.println(inputName+","+filename);

                    strBuf.append("Content-Type:" + contentType + "\r\n\r\n");
                    out.write(strBuf.toString().getBytes());
                    DataInputStream in = new DataInputStream(new FileInputStream(file));
                    int bytes = 0;
                    byte[] bufferOut = new byte[1024];
                    while ((bytes = in.read(bufferOut)) != -1) {
                        out.write(bufferOut, 0, bytes);
                    }
                    in.close();
                }
            }
            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();

            // 读取返回数据
            StringBuffer strBuf = new StringBuffer();
            //如果返回有强制gzip压缩 则必须如此解析。
            //无gzip压缩 直接 BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//            GZIPInputStream gZipS = null;
//            gZipS =new GZIPInputStream(conn.getInputStream());
//            BufferedReader reader = new BufferedReader(new InputStreamReader(gZipS, "UTF-8"));
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            int ss;
            while ((ss = reader.read()) != -1) {
                strBuf.append((char) ss);
            }
            res = strBuf.toString();
            reader.close();
//            gZipS.close();
            reader = null;
        } catch (Exception e) {
            System.out.println("发送POST请求出错。" + urlStr);
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {

        Map<String, String> params = new HashMap<String, String>();
        //普通参数：可以设置多个input的name，value
        params.put("type", "1");
        params.put("template_group_id", "1");
        params.put("account_id", "3");
        params.put("appkey", "B06618AE37F34D03AB61F07CF058996F");
        params.put("sign", "395f78769c7ae93aa90e674acf8ffe00");
        params.put("ts", "1618563977548");

        //文件：设置file的name，路径
        Map<String, String> fileMap = new HashMap<String, String>();
//        fileMap.put("/Users/mort/Downloads/WechatIMG30.png", "/Users/mort/Downloads/WechatIMG30.png");
//        fileMap.put("file", "/Users/mort/Downloads/600X400");
        fileMap.put("file", "/Users/mort/Downloads/640*400.jpeg");
//        fileMap.put("file", "/Users/mort/Downloads/WechatIMG30.png");

        String result = formUpload("http://cm.bi.com/takumi/api/open_api/launch/cpc/meta_data/support/upload", params, fileMap);
        System.out.println("args = " + JSON.toJSON(result));
    }


    /**
     * 服务端接收
     *
     *
     *     @PostMapping("/support/upload")
     *     public JSONObject creativeUpload(@RequestParam(value = "account_id") Integer accountId,
     *                                      @RequestParam(value = "template_id", required = false) Integer templateId,
     *                                      @RequestParam(value = "template_group_id", required = false) Integer templateGroupId,
     *                                      @RequestParam("type") Integer type,
     *                                      @RequestParam("file") MultipartFile multipartFile,
     *                                      @RequestParam(value = "save", defaultValue = "false") Boolean saveToCreativeCenter) throws IOException {
     *
     *
     *                                      }
     */
}
