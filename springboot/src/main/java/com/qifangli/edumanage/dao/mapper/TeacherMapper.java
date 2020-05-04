package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.Teacher;
import org.apache.ibatis.annotations.Param;

public interface TeacherMapper {

    Teacher findTeacherById(@Param("id") String id);

    Teacher findTeacherByIdAndPwd(@Param("id") String id, @Param("pwd") String pwd);

}
