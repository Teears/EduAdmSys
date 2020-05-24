package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;

@Mapper
public interface StudentMapper {

   Student findStudentById(@Param("id") String id);

   int updatePwdByStuId(@Param("id") String id,@Param("newPwd") String newPwd);

   List<Student> findStuByDptAndGrade(@Param("dpt") String dpt,@Param("grade") String grade);

   int insertStu(Student student) throws DuplicateKeyException;

   int updateStu(Student student);

   int deleteStuById(@Param("id") String id);

   int updateStuTel(@Param("id") String id,@Param("telephone") String telephone);
}

