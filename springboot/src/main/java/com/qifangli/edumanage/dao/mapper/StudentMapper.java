package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
   List<Student> getStudent();
}

