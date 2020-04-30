package com.qifangli.edumanage.service;

import com.qifangli.edumanage.dao.entity.Student;

public interface StudentService {
//    Student getUserById(String id);
    Student findStudentByIdAndPwd(String id, String pass);
}
