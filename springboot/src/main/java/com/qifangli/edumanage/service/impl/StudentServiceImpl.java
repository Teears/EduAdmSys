package com.qifangli.edumanage.service.impl;

import com.qifangli.edumanage.dao.entity.Student;
import com.qifangli.edumanage.dao.mapper.StudentMapper;
import com.qifangli.edumanage.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> getStudent() {
        System.out.println("/getStudent start...");
        return studentMapper.getStudent();
    }
}
