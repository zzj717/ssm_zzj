package com.fh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.entity.po.Student;
import com.fh.entity.vo.StudentAll;
import com.fh.entity.vo.StudentSearch;

import java.util.List;

public interface StudentDao extends BaseMapper<Student> {
    List<StudentAll> querylist(StudentSearch search);

    long getcount(StudentSearch search);
}
