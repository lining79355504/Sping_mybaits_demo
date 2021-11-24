package com.demo.file;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author mort
 * @Description
 * @date 2021/8/26
 **/
public class ExcelExportUtils {

    private static final Logger logger = LoggerFactory.getLogger(ExcelExportUtils.class);


    //导出文件到本地  带路径
    public static <T> void export(List<T> data, String namePrefix) {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(generateFileName(namePrefix));
            export(data, outputStream);

        } catch (Exception e) {
            logger.error("error ", e);

        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //web导出文件  带路径
    public static <T> void export(List<T> data, String namePrefix, HttpServletResponse response) {
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            response.reset();
            //设置响应头，
            response.setHeader("Content-disposition", "attachment; filename=" + generateFileName(namePrefix));
            response.setContentType("application/msexcel");

            export(data, outputStream);
        } catch (Exception e) {
            logger.error("error ", e);

        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static <T> void export(List<T> data, OutputStream op) {
        SXSSFWorkbook workbook = new SXSSFWorkbook(1000);
        Sheet sheet = workbook.createSheet();
        try {
            T object = data.get(0);
            Field[] fields = object.getClass().getDeclaredFields();
            Set<Field> exportField = new HashSet<>();
            //build header
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].isAnnotationPresent(ExcelTitle.class)) {
                    exportField.add(fields[i]);
                }
            }
            Row headRow = sheet.createRow(0);
            //set header
            Object[] exportHeaderArray = exportField.toArray();
            for (int i = 0; i < exportField.size(); i++) {
                Field field = (Field) exportHeaderArray[i];
                String title = field.getAnnotation(ExcelTitle.class).title();
                headRow.createCell(i).setCellValue(title);
            }
            //build data
            Object[] dataObjects = data.toArray();
            for (int i = 0; i < data.size(); i++) {
                Object item = dataObjects[i];
                Row dataRow = sheet.createRow(i + 1);
                Field[] itemField = item.getClass().getDeclaredFields();
                for (int i1 = 0; i1 < itemField.length; i1++) {
                    Field dataItemField = itemField[i1];
                    if (exportField.contains(dataItemField)) {
                        dataItemField.setAccessible(true);
                        try {
                            dataRow.createCell(i1).setCellValue(String.valueOf(dataItemField.get(item)));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            workbook.write(op);

        } catch (Exception e) {
            logger.error("", e);
        } finally {
            workbook.dispose();
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static File generateFileName(String namePrefix) {
        File file = new File(namePrefix + ".xlsx");
        return file;
    }

    public static void main(String[] args) {
        List<TestDto> data = new ArrayList();
        data.add(new TestDto(1, "1"));
        data.add(new TestDto(1, "2"));
        data.add(new TestDto(1, "3"));
        data.add(new TestDto(1, "1"));
        export(data, "test");
    }


}
