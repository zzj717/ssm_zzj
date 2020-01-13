package com.fh.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fh.conmon.annotaction.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@TableName("ssmp_student")
public class Student {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @Excel(name="学生姓名",value = "name")
    private String name;
    @Excel(name="学生年龄",value = "age")
    private Integer age;
    @Excel(name="学生生日",value = "birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String address;
    private String imgpath;
    private Integer isdel;
    private String addip;

    public String getAddip() {
        return addip;
    }

    public void setAddip(String addip) {
        this.addip = addip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }
}
