package com.qifangli.edumanage.service;

import com.qifangli.edumanage.dao.entity.Teacher;

import java.util.List;
import java.util.Map;

public interface TeacherService {

    Teacher findTeacherById(String id);

    int updatePwdByTeaId(String id,String newPwd);

    List<Teacher> findTeacherByDpt(String dpt);

    /**
     * admin,导入表格，批量添加教师信息
     * @param entityList
     * @return
     */
    Map<String,Object> addTea(List<Object> entityList);

    /**
     * admin,单个添加教师信息
     * @param teacher
     * @return
     */
    int insertTea(Teacher teacher);

    int updateTea(Teacher teacher);

    int deleteTeaById(String id);

    int updateTeaTel(String id, String telephone);

}
