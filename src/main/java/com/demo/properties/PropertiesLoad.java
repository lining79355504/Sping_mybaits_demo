package com.demo.properties;

import java.io.*;
import java.util.Iterator;
import java.util.Properties;

/**
 * Created by mort on 11/05/2017.
 */
public  class  PropertiesLoad {

    public static void main(String[] args) {


        Properties pro = new Properties();

//        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("dev_config.properties");
        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream("dev_config.properties"));

            try {
                pro.load(inputStream);
                Iterator<String> it=pro.stringPropertyNames().iterator();
                while (it.hasNext()){
                    String key = it.next();
                    System.out.println( key +"=    " + pro.getProperty(key));
                }
            } catch (IOException e) {

                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            pro.load(new InputStream() {
                @Override
                public int read() throws IOException {

                    return 0;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
