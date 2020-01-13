package com.fh.service;

import com.fh.entity.po.Sarea;
import com.fh.entity.po.Student;
import com.fh.entity.vo.StudentSearch;

import java.util.List;

public interface StudentService {
    List<Sarea> querylistarea();

    void addStudent(Student student);

    String querylistStudent(StudentSearch search);

    Student querybyidstudent(Integer id);

    void updatestudent(Student student);

    void deletestudent(Student student);
}
