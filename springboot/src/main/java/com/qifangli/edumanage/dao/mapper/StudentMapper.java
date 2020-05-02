package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {

   public Student findStudentById(@Param("id") String id);

   public List<Student> findAllStudent();

//   public void updateStudent(Student student);
//
//   public void addStudent(Student student);
//
//   public void deleteStudent(String id);

   public Student findStudentByIdAndPwd(@Param("id") String id, @Param("pwd") String pwd);

//   public void updatePwd(String id, String pwd);

}

