package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TeacherMapper {

    Teacher findTeacherById(@Param("id") String id);

}
