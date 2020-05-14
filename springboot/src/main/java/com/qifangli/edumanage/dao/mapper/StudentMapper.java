package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {

   Student findStudentById(@Param("id") String id);

   void updatePwdByStuId(@Param("id") String id,@Param("newPwd") String newPwd);

   List<Student> findStuByDpt(@Param("dpt") String dpt);

   int insertStu(Student student);
}

