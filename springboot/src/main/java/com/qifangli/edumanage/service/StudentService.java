package com.qifangli.edumanage.service;

import com.qifangli.edumanage.dao.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

public interface StudentService {
    List<Student> getStudent();
}
