package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {
   @Select("select * from tbl_student")
   @Results({
           @Result(property = "id", column = "stu_no"),
           @Result(property = "name", column = "stu_name")
   })
   List<Student> getStudent();
}

