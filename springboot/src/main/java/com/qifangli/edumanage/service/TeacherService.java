package com.qifangli.edumanage.service;

import com.qifangli.edumanage.dao.entity.Teacher;

import java.util.List;
import java.util.Map;

public interface TeacherService {

    Teacher findTeacherById(String id);

    void updatePwdByTeaId(String id,String newPwd);

    List<Teacher> findTeacherByDpt(String dpt);

    Map<String,Object> addTea(List<Object> entityList);

    int insertTea(Teacher teacher);

    int updateTea(Teacher teacher);

    int deleteTeaById(String id);

}
