package com.demo.berkeleyDB;

/**
 * Author:  lining17
 * Date :  2019-07-16
 */

import com.sleepycat.je.*;

import java.io.*;
import java.util.UUID;

public class Test {

    private static String data = "";
    static {


    }

    public static void main(String[] args) {

        String testStr  ="asdsadas";
        byte[] fileByte ;
        String testFile = "/Users/mort/Downloads/test.txt";
        File file = new File(testFile);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                testStr=testStr+tempString;
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }



        Environment myDbEnvironment = null;
        Database myDatabase = null;

        try {
            String path = "testDB";
            File f = new File(path);
            if (!f.exists()) {
                f.mkdirs();
            }
            // Open the environment, creating one if it does not exist
            EnvironmentConfig envConfig = new EnvironmentConfig();
            envConfig.setAllowCreate(true);
            envConfig.setTransactional(true);
            envConfig.setTxnNoSync(false);
            myDbEnvironment = new Environment(new File(path),
                    envConfig);


            // Open the database, creating one if it does not exist
            DatabaseConfig dbConfig = new DatabaseConfig();
            dbConfig.setAllowCreate(true);
            myDatabase = myDbEnvironment.openDatabase(null,
                    "TestDatabase", dbConfig);


            for (int i = 0; i < 3; i++) {
                System.out.println("args = [" +" start write" + "]");
                write(myDatabase, i+12312321, testStr);
            }
            getTest(myDatabase,"myKey12312321");
//            listAll(myDatabase);
            myDbEnvironment.sync();  //data save flush to disk
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                myDatabase.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void write(Database myDatabase, int i , String dataStr) {

        try {

            String key = "myKey" + i;
            System.out.println("Key = [" + key + "], i = [" + i + "]");

            DatabaseEntry theKey = new DatabaseEntry(key.getBytes("UTF-8"));
            DatabaseEntry theData = new DatabaseEntry(dataStr.getBytes("UTF-8"));
            myDatabase.put(null, theKey, theData);

//            getTest(myDatabase, key);
        } catch (DatabaseException dbe) {
            //  Exception handling
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void getTest(Database myDatabase, String key) {

        try {
            DatabaseEntry theKey = new DatabaseEntry(key.getBytes("UTF-8"));
            DatabaseEntry theData = new DatabaseEntry();
            if (myDatabase.get(null, theKey, theData, LockMode.DEFAULT) ==
                    OperationStatus.SUCCESS) {

                // Translate theData into a String.
                byte[] retData = theData.getData();
                String foundData = new String(retData, "UTF-8");
                System.out.println("key: '" + key + "' data: '" +
                        foundData + "'.");
            } else {
                System.out.println("No record found with key '" + key + "'.");
            }
        } catch (Exception e) {

        }

    }


    private static void listAll(Database myDatabase) {
        Cursor myCursor = null;

        try {
            myCursor = myDatabase.openCursor(null, null);

            // Cursors returns records as pairs of DatabaseEntry objects
            DatabaseEntry foundKey = new DatabaseEntry();
            DatabaseEntry foundData = new DatabaseEntry();

            // Retrieve records with calls to getNext() until the
            // return status is not OperationStatus.SUCCESS
            while (myCursor.getNext(foundKey, foundData, LockMode.DEFAULT) ==
                    OperationStatus.SUCCESS) {
                String keyString = new String(foundKey.getData(), "UTF-8");
                String dataString = new String(foundData.getData(), "UTF-8");
                System.out.println("Key| Data : " + keyString + " | " +
                        dataString + "");
            }
        } catch (DatabaseException de) {
            System.err.println("Error reading from database: " + de);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (myCursor != null) {
                    myCursor.close();
                }
            } catch (DatabaseException dbe) {
                System.err.println("Error closing cursor: " + dbe.toString());
            }
        }
    }
}
