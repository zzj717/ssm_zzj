package com.fh.utils;

import com.alibaba.fastjson.JSONArray;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonUtil {
           public static void jsonArray(HttpServletResponse response,Object obj){
        	     String jsonString = JSONArray.toJSONString(obj);
        	     //设置UTF-8编码
        	     response.setCharacterEncoding("UTF-8");
        	     //设置json
        	     response.setContentType("application/json");
        	     //获取相应流
        	    
				try {
					 PrintWriter writer = response.getWriter();
					//写入
	        	     writer.write(jsonString);
	        	     //关闭流
	        	     writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	     
           }
           
           
           public static void jsonArray(HttpServletResponse response,String obj){
      	    
      	     //设置UTF-8编码
      	     response.setCharacterEncoding("UTF-8");
      	     //设置json
      	     response.setContentType("application/json");
      	     //获取相应流
      	    
				try {
					 PrintWriter writer = response.getWriter();
					//写入
	        	     writer.write(obj);
	        	     //关闭流
	        	     writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
      	     
         }
}
