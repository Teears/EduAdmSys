package com.qifangli.edumanage.controller;

import com.qifangli.edumanage.dao.entity.Student;
import com.qifangli.edumanage.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //将类中所有控制器的方法返回值转为json格式，并响应前端 = @Controller + @responseBody
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping("/getStudent")
    public List<Student> getStudent(){
        System.out.println("controller is running");
        return studentService.getStudent();
    }

//    @RequestMapping("test1")
//    public String hello(){
//        System.out.println("test1 is running");
//        return "this is test1";
//    }
}