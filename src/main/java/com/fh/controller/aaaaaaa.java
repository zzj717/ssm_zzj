package com.fh.controller;

import com.fh.entity.po.Student;
import com.fh.service.StudentService;
import com.fh.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("zzj/")
public class aaaaaaa {
    @Autowired
    private StudentService studentservice;
    @Resource
    HttpServletResponse response;
    // 导出excel
    @RequestMapping("getExcel")
    public void getExcel(HttpServletResponse response){
        List<Student> getquerylist = studentservice.getquerylist();
        ExcelUtils.downExcel(getquerylist,response);
    }
}
