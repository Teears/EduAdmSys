package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.CourseArrange;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;

@Mapper
public interface CourseArrangeMapper {

    /**
     * 查某学期的教室*课表
     * @param term
     * @param spot
     * @return
     */
    List<CourseArrange> findByTermAndSpot(@Param("term") String term, @Param("spot") String spot);

    /**
     * 学生，查自己的学期课表
     * @param term
     * @param id
     * @return
     */
    List<CourseArrange> findByTermAndStuId(@Param("term") String term, @Param("id") String id);

    /**
     * 教师，查自己的学期课表
     * @param term
     * @param id
     * @return
     */
    List<CourseArrange> findByTermAndTeaId(@Param("term") String term, @Param("id") String id);

    /**
     * xx学院xx年级在新的一学期的排课
     * @param term
     * @param dpt
     * @param grade
     * @return
     */
    List<CourseArrange> findByTermDptGrade(@Param("term") String term, @Param("dpt") String dpt,@Param("grade") String grade);

    /**
     * 学生，选课，该门课的选课人数加一
     * @param id
     * @throws DataAccessException 如果到最大选课人数抛出
     */
    void updateSelectedAdd(@Param("id") String id) throws DataAccessException;

    /**
     * 学生，退课，该门课的选课人数减一
     * @param id
     * @throws DataAccessException 如果到最低选课人数抛出
     */
    void updateSelectedSub(@Param("id") String id) throws DataAccessException;

    /**
     * 排课，添加一条课程安排
     * @param courseArrange
     * @return
     * @throws DuplicateKeyException
     */
    int insertArrange(CourseArrange courseArrange) throws DuplicateKeyException;

    /**
     * 排课，删除课程安排
     * @param id
     * @return
     */
    int deleteArrangeById(@Param("id") String id);

    /**
     * 排课，更新课程安排
     * @param courseArrange
     * @return
     */
    int updateArrange(CourseArrange courseArrange);
}
