package com.fh.controller;

import com.fh.conmon.ResponseData;
import com.fh.entity.po.Sarea;
import com.fh.entity.po.Student;
import com.fh.entity.vo.StudentSearch;
import com.fh.service.StudentService;
import com.fh.utils.JsonUtil;
import com.fh.utils.OssloadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("student/")
@CrossOrigin
public class StudentController {
     @Autowired
      private StudentService studentservice;
      @Resource
      private HttpServletRequest request;
      @Resource
      private HttpServletResponse response;

      //  分页数据查询
      @PostMapping("querylistStudent")
      public void querylistStudent(StudentSearch search){
          try {
              String omg=studentservice.querylistStudent(search);
              JsonUtil.jsonArray(response,omg);
          }catch (Exception e){
              e.printStackTrace();
          }
      }
      // 增加学生管路系统
       @PostMapping("addStudent")
       public ResponseData addStudent(Student student){
           try {
               studentservice.addStudent(student);
               return ResponseData.success(null);
           }catch (Exception e){
               e.printStackTrace();
               return ResponseData.error(e.getMessage());
           }
       }
       // 回显学生管理系统
       @PostMapping("querybyidstudent")
      public ResponseData querybyidstudent(Integer id){
          try {
             Student dent= studentservice .querybyidstudent(id);
              return ResponseData.success(dent);
          }catch (Exception e){
              e.printStackTrace();
              return ResponseData.error(e.getMessage());
          }
      }
      //  修改学生管理系统
      @PostMapping("updatestudent")
      public ResponseData updatestudent(Student student){
          try {
             studentservice .updatestudent(student);
              return ResponseData.success(null);
          }catch (Exception e){
              e.printStackTrace();
              return ResponseData.error(e.getMessage());
          }
      }
      // 删除学生管理系统
       @PostMapping("deletestudent")
       public ResponseData deletestudent(Student student){
           try {
               studentservice .deletestudent(student);
               return ResponseData.success(null);
           }catch (Exception e){
               e.printStackTrace();
               return ResponseData.error(e.getMessage());
           }
       }
     // 地区接口
     @PostMapping("querylistarea")
     public ResponseData querylistarea(){
         try {
             List<Sarea> list=studentservice.querylistarea();
             return ResponseData.success(list);
         }catch (Exception e){
             e.printStackTrace();
             return ResponseData.error(e.getMessage());
         }
     }

     // 图片处理  fileimg
    @PostMapping("uploadimg")
     public ResponseData uploadimg(MultipartFile picture){
         try {

             String s = OssloadUtil.uploadFile(request, picture);
             return ResponseData.success(s);
         }catch (Exception e){
             e.printStackTrace();
             return ResponseData.error(e.getMessage());
         }
     }


}
