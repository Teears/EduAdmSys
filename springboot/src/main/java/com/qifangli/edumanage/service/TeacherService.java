package com.qifangli.edumanage.service;

import com.qifangli.edumanage.dao.entity.Teacher;

public interface TeacherService {

    Teacher findTeacherById(String id);

    void updatePwdByTeaId(String id,String newPwd);

}
