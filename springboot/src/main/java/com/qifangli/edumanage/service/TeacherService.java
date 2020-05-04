package com.qifangli.edumanage.service;

import com.qifangli.edumanage.dao.entity.Teacher;

public interface TeacherService {

    Teacher findTeacherByIdAndPwd(String id, String pwd);

}
