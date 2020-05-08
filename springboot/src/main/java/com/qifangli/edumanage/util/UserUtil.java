package com.qifangli.edumanage.util;

import com.qifangli.edumanage.dao.entity.Student;
import com.qifangli.edumanage.dao.entity.Teacher;
import com.qifangli.edumanage.service.StudentService;
import com.qifangli.edumanage.service.TeacherService;

import javax.annotation.Resource;

public class UserUtil {
    @Resource
    private static StudentService studentService;
    @Resource
    private static TeacherService teacherService;

    public static String getPwdById (String id){
        Integer slog = 10;
        Integer tlog = 8;
        String pwd = null;
        if(id.length()== slog){
            Student student = studentService.findStudentById(id);
            if(student == null){
                return null;
            }
            return student.getPass();
        }else if(id.length()== tlog){
            Teacher teacher = teacherService.findTeacherById(id);
            if (teacher == null){
                return null;
            }
            return pwd = teacher.getPass();
        }
        return pwd;
    }

    public static <T>T getUserById (String id){
        Integer slog = 10;
        Integer tlog = 8;
        T t = null;
        if(id.length()== slog){
            Student student = studentService.findStudentById(id);
            if(student == null){
                return null;
            }
            return (T) student;
        }else if(id.length()== tlog){
            Teacher teacher = teacherService.findTeacherById(id);
            if (teacher == null){
                return null;
            }
            return (T) teacher;
        }
        return null;
    }
}
