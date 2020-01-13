package com.fh.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fh.dao.SareaDao;
import com.fh.dao.StudentDao;
import com.fh.entity.po.Sarea;
import com.fh.entity.po.Student;
import com.fh.entity.vo.StudentAll;
import com.fh.entity.vo.StudentSearch;
import com.fh.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class StudentServiceImpl implements StudentService {
       @Resource
       private StudentDao studentdao;
       @Resource
       private SareaDao sareadao;
       @Resource
       private HttpServletRequest request;
       // 地区接口
       @Transactional(readOnly = true) // 只读事物
       @Override
       public List<Sarea> querylistarea() {
              return sareadao.selectList(null);
       }

       @Override
       public void addStudent(Student student) {
              // 删除逻辑处理
              student.setIsdel(1);
              // 年龄处理
              Date date=new Date();
              int year = date.getYear();
              int year1 = student.getBirthday().getYear();
              student.setAge(year-year1);
              // 本机ip
              String remoteAddr = request.getRemoteAddr();
              student.setAddip(remoteAddr);
              // 增加方法
              studentdao.insert(student);
       }

    @Override
    public String querylistStudent(StudentSearch search) {
        List<StudentAll> list = studentdao.querylist(search);
        long count =studentdao.getcount(search);
        Map<String,Object> map=new HashMap<>();
        map.put("draw",search.getDraw());
        map.put("recordsTotal",(int)count);
        map.put("recordsFiltered",(int)count);
        map.put("data",list);
        String s = JSONObject.toJSONString(map);
        return s;
    }

    @Override
    public Student querybyidstudent(Integer id) {

        return studentdao.selectById(id);
    }

    @Override
    public void updatestudent(Student student) {
        // 年龄处理
        Date date=new Date();
        int year = date.getYear();
        int year1 = student.getBirthday().getYear();
        student.setAge(year-year1);

        studentdao.updateById(student);
    }

    @Override
    public void deletestudent(Student student) {
        student.setIsdel(0);
        studentdao.updateById(student);
    }

    @Override
    public List<Student> getquerylist() {
        return studentdao.selectList(null);
    }
}
