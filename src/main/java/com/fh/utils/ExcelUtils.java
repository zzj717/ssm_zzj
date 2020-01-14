package com.fh.utils;

import com.fh.conmon.annotaction.Excel;
import com.fh.conmon.annotaction.ExcelTitel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ExcelUtils {

        public static void downExcel(List list, HttpServletResponse response){
            // 类对象
            Object obj = list.get(0);
            Class clazz = obj.getClass();
            // 获取类上的自定义注解
            ExcelTitel excelTitel = (ExcelTitel) clazz.getAnnotation(ExcelTitel.class);
            //创建Excel
            XSSFWorkbook book=new XSSFWorkbook();
            //创建Excel 页                    自定义方法上的头信息放上面
            XSSFSheet sheet = book.createSheet(excelTitel.name());
            XSSFRow row = sheet.createRow(0);
            //处理标题头  获取当前类的所有属性
            Field[] fields =  clazz.getDeclaredFields();
            int count=0;
            for (int i = 0; i <fields.length ; i++) {
                Field field=  fields[i];
                // 获取当前类中属性有自定义头信息的 注解属性
                Excel annotation = field.getAnnotation(Excel.class);
                if(annotation!=null){
                    // 创建row行                                          自定义的在属性上面的 name值
                    row.createCell(count).setCellValue(annotation.name());
                    count++;
                }
            }

            // 数据便利
            for (int i = 0; i <list.size() ; i++) {
                Object obj2 = list.get(i);
                // 创建行 
                XSSFRow row1 = sheet.createRow(i + 1);
                int count2=0;
                // 再次便利属性
                for (int j = 0; j <fields.length ; j++) {
                    Field  field= fields[j];
                    // 查看每个属性是否加上了 自定义注解 如果是就返回true
                    boolean resent = field.isAnnotationPresent(Excel.class);
                    if(resent){
                        //创建cell行
                        XSSFCell cell = row1.createCell(count2);
                        try {
                          // 因为有的属性是私有化的 所以需要暴力访问
                            field.setAccessible(true);
                            Object obj3 = field.get(obj2);
                            if(obj3!=null){
                                Class type = field.getType();
                                //处理日期类型的字段
                                if(type== Date.class){
                                    //格式化日期
                                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                                    Date date= (Date) obj3;
                                    String format = sdf.format(date);
                                    cell.setCellValue(format);
                                }else {
                                    cell.setCellValue(obj3.toString());
                                }
                            }else {
                                cell.setCellValue("");
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        count2++;
                    }
                }
            }

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment; filename=\""+ UUID.randomUUID().toString()+".xlsx\"");
            ServletOutputStream outputStream = null;
            try {
                outputStream = response.getOutputStream();
                book.write(outputStream);
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
