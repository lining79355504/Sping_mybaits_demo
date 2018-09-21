package com.demo.file;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:  lining17
 * Date :  2018/8/21
 */
@Service
public class ExcelUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    public static String httpPostWithJSON(String url) throws Exception {

        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient client = HttpClients.createDefault();
        String respContent = null;


//        json方式
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("userId", "admin");
        jsonParam.put("useRange", "123456");
        jsonParam.put("points", "123456");
        jsonParam.put("memo", "123456");
        StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");//解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);

        System.out.println();


//        表单方式
//        List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
//        pairList.add(new BasicNameValuePair("name", "admin"));
//        pairList.add(new BasicNameValuePair("pass", "123456"));
//        httpPost.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));


        HttpResponse resp = client.execute(httpPost);
        if (resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity he = resp.getEntity();
            respContent = EntityUtils.toString(he, "UTF-8");
        }
        return respContent;
    }

    public void excelHandler() {


        String excelPath = "/Users/mort/Downloads/商户报错系统升级补偿.xlsx";


        try {
            File excel = new File(excelPath);
            if (excel.isFile() && excel.exists()) {   //判断文件是否存在

                String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！！！！！
                Workbook wb;
                //根据文件后缀（xls/xlsx）进行判断
                if ("xls".equals(split[1])) {
                    FileInputStream fis = new FileInputStream(excel);   //文件流对象
                    wb = new HSSFWorkbook(fis);
                } else if ("xlsx".equals(split[1])) {
                    wb = new XSSFWorkbook(excel);
                } else {
                    System.out.println("文件类型错误!");
                    return;
                }

                //开始解析
                Sheet sheet = wb.getSheetAt(0);     //读取sheet 0

                int firstRowIndex = sheet.getFirstRowNum() + 1;   //第一行是列名，所以不读
                int lastRowIndex = sheet.getLastRowNum();
                System.out.println("firstRowIndex: " + firstRowIndex);
                System.out.println("lastRowIndex: " + lastRowIndex);

                for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
                    System.out.println("rIndex: " + rIndex);
                    Row row = sheet.getRow(rIndex);
                    if (row != null) {
                        int firstCellIndex = row.getFirstCellNum();
                        int lastCellIndex = row.getLastCellNum();
                        String userID = "";
                        String platFormType = "";
                        String outOrderId = "";
                        String outBiz = "";

                        for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列
                            Cell cell = row.getCell(cIndex);
                            if (cell != null) {
                                cell.setCellType(CellType.STRING);
                                System.out.println(cell.toString());
//                            System.out.println(cell.getStringCellValue());

                                switch (cIndex) {

                                    case 0:
                                        userID = cell.toString();
                                        break;
                                    case 1:
                                        outBiz = cell.toString();
                                        break;
                                    case 2:
                                        outOrderId = cell.toString();
                                        break;
                                    case 3:
                                        platFormType = cell.toString();
                                        break;
                                }
                            }
                        }

                        String url = "http://localhost:8080/apitool/point_detail?userID=" + userID
                                + "&platFormType=" + platFormType
                                + "&outOrderId=" + outOrderId
                                + "&outBiz=" + outBiz;


                        url = "http://test.infoplatform.dianping.com/oss/point/ajax/pointReissueAjax?points=10&userId=" +
                                userID + "&useRange=1&memo="+URLEncoder.encode("商户报错系统升级积分补偿");

                        logger.info("url is {}" , url);
//                    HttpClient client = new DefaultHttpClient();
//                    System.out.println("url"+ url);
//                    //发送get请求
//                    HttpGet request = new HttpGet(url);
//                    HttpResponse response = client.execute(request);
//
//                    /**请求发送成功，并得到响应**/
//                    if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//                        /**读取服务器返回过来的json字符串数据**/
//                        String strResult = EntityUtils.toString(response.getEntity());
//
//                        System.out.println("strResult is "+ strResult);
//                    }

                        // online
                        String cookie = "_ga=GA1.2.2110259256.1528704657; ssoid=ecadf18707*94911bb58658e82fa6df6; JSESSIONID=5EFD76C3B02B440B4E1A557E2AC6F458";

                        //offline
//                        cookie = "_hc.v=\"\\\"5d1b0ed0-a075-4253-9309-fdc108e25295.1493087242\\\"\"; _lxsdk_cuid=15e7f4f19f3c8-09dc4cfa04e3d7-31637e01-13c680-15e7f4f19f3c8; _lxsdk=15e7f4f19f3c8-09dc4cfa04e3d7-31637e01-13c680-15e7f4f19f3c8; ctu=4da2a4ca1e00ee2316d666c2186602675b47c38565289ff319b5df388a6ff16e; Hm_lvt_dbeeb675516927da776beeb1d9802bd4=1507864879; __mta=151436550.1507864857173.1509515931478.1509515942206.19; s_ViewType=10; aburl=1; _tr.u=H28fAgtC32iykA5q; ua=ACE_2598; __utma=1.1480696191.1493106114.1503404145.1517387691.12; cityid=1; switchcityflashtoast=1; cy=1; cye=shanghai; ssoid=dd12d8a20a*24a55a353a18837d871a2; JSESSIONID=8D0751EC13E971D6D6113EDAECE21F9C";

                        URL urlStr = new URL(url);
                        URLConnection conn = urlStr.openConnection();
                        conn.setRequestProperty("Cookie", cookie);
                        conn.setDoInput(true);
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line = null;
                        while ((line = br.readLine()) != null) {
                            sb.append(line);
                        }
                        System.out.println("请求响应结果：" + sb);
                        Map ret = JSONObject.parseObject(JSONObject.parse(sb.toString()).toString(),Map.class);
                        logger.info("ret is {}" , ret);
                        if(!ret.get("result").equals("success")){
                            logger.error("fail url is {}",url);
                        }

                    }
                }
            } else {
                System.out.println("找不到指定的文件");
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public void cookerSet() {


        String url = "http://localhost:8080/oss/point/ajax/pointReissueAjax?points=10&userId=" +
                121212 + "&useRange=1&memo="+URLEncoder.encode("商户报错系统升级积分补偿");

        url = "http://infoplatform.dper.com/";


        String cookie = "_ga=GA1.2.2110259256.1528704657; ssoid=ecadf18707*94911bb58658e82fa6df6; JSESSIONID=5EFD76C3B02B440B4E1A557E2AC6F458";

        URL urlStr = null;
        try {
//            url = URLEncoder.encode(url, "utf-8");
            urlStr = new URL(url);

            URLConnection conn = urlStr.openConnection();
            conn.setRequestProperty("Cookie", cookie);
            conn.setDoInput(true);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            System.out.println("请求响应结果：" + sb);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String retTest(Integer param){
        return String.valueOf(param);
    }
}
