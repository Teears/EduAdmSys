package com.qifangli.edumanage.controller;

import com.qifangli.edumanage.dao.entity.Student;
import com.qifangli.edumanage.service.StudentService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Mapper
@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/getStudent")
    public List<Student> getStudent(){
        return studentService.getStudent();
    }
}

