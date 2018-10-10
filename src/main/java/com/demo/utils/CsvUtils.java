package com.demo.utils;

import com.csvreader.CsvWriter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;

public class CsvUtils {




    public static String getRandomString(int length){
        //产生随机数
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        //循环length次
        for(int i=0; i<length; i++){
            //产生0-2个随机数，既与a-z，A-Z，0-9三种可能
            int number=random.nextInt(3);
            long result=0;
            switch(number){
                //如果number产生的是数字0；
                case 0:
                    //产生A-Z的ASCII码
                    result=Math.round(Math.random()*25+65);
                    //将ASCII码转换成字符
                    sb.append(String.valueOf((char)result));
                    break;
                case 1:
                    //产生a-z的ASCII码
                    result=Math.round(Math.random()*25+97);
                    sb.append(String.valueOf((char)result));
                    break;
                case 2:
                    //产生0-9的数字
                    sb.append(String.valueOf
                            (new Random().nextInt(10)));
                    break;
            }
        }
        return sb.toString();
    }


    public static void write(){

        //3000000000L+(int)(Math.random()*1000000000)
        String filePath = "/Users/lining/Sping_mybaits_demo/test.csv";

        try {
            // 创建CSV写对象
            CsvWriter csvWriter = new CsvWriter(filePath,',', Charset.forName("UTF-8"));
            //CsvWriter csvWriter = new CsvWriter(filePath);

            // 写表头
            String[] headers = {"dto","map"};
            csvWriter.writeRecord(headers);
            for (int i = 0; i < 20; i++) {
                String[] content = { "张山", "34"};
                csvWriter.writeRecord(content);
            }
            csvWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        write();
    }
}
