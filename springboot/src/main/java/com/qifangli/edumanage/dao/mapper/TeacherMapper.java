package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.Teacher;
import org.apache.ibatis.annotations.Param;

public interface TeacherMapper {

    public Teacher findTeacherById(@Param("id") String id);
}
