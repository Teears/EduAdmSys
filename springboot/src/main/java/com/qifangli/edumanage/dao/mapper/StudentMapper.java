package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {

   Student findStudentById(@Param("id") String id);

   Student findStudentByIdAndPwd(@Param("id") String id, @Param("pwd") String pwd);

}

