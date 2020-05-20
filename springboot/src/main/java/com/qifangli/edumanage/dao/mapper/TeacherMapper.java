package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;

@Mapper
public interface TeacherMapper {

    Teacher findTeacherById(@Param("id") String id);

    void updatePwdByTeaId(@Param("id") String id,@Param("newPwd") String newPwd);

    List<Teacher> findTeacherByDpt(@Param("dpt") String dpt);

    int insertTea(Teacher teacher) throws DuplicateKeyException;

    int updateTea(Teacher teacher);

    int deleteTeaById(@Param("id") String id);
}
