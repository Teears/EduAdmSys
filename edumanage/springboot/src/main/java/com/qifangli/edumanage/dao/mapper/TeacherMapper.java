package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;

@Mapper
public interface TeacherMapper {

    /**
     * 通过工号查教室信息
     * @param id
     * @return
     */
    Teacher findTeacherById(@Param("id") String id);

    /**
     * 通过工号修改密码
     * @param id
     * @param newPwd
     */
    int updatePwdByTeaId(@Param("id") String id,@Param("newPwd") String newPwd);

    /**
     * 查询这个学院的所有教师
     * @param dpt
     * @return
     */
    List<Teacher> findTeacherByDpt(@Param("dpt") String dpt);

    /**
     * 新增教师
     * @param teacher
     * @return
     * @throws DuplicateKeyException 如果工号重复抛出
     */
    int insertTea(Teacher teacher) throws DuplicateKeyException;

    /**
     * 通过工号更新教师信息
     * @param teacher
     * @return
     */
    int updateTea(Teacher teacher);

    /**
     * 通过工号删除教师
     * @param id
     * @return
     */
    int deleteTeaById(@Param("id") String id);

    int updateTeaTel(@Param("id") String is, @Param("telephone") String telephone);
}
