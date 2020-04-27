package com.qifangli.edumanage.service.impl;

import com.qifangli.edumanage.dao.entity.Student;
import com.qifangli.edumanage.dao.mapper.StudentMapper;
import com.qifangli.edumanage.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
//    @Autowired
    private StudentMapper studentMapper;
    @Resource
    public void setStudentMapper(StudentMapper studentMapper){
        this.studentMapper = studentMapper;
    }

    @Override
    public List<Student> getStudent() {
        System.out.println("/getStudent start...");
        return studentMapper.getStudent();
    }
}
