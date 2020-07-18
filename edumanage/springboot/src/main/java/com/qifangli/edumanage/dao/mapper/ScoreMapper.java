package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.Score;
import com.qifangli.edumanage.dao.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface ScoreMapper {

    /**
     * 查询这个学生的所有成绩，未使用
     * @param id
     * @return
     */
    Score findScoreByStuId(@Param("id") String id);

    /**
     * 学生选课,在score表里添加
     * @param stuId
     * @param teaCrsId
     * @throws DataAccessException 如果重复选课抛出
     */
    void addCrs(@Param("stuId") String stuId,@Param("teaCrsId") String teaCrsId) throws DataAccessException;

    /**
     * 学生退课,在score表里删除
     * @param stuId
     * @param teaCrsId
     * @throws DataAccessException 如果达到最大退课人数抛出
     */
    void deleteCrs(@Param("stuId") String stuId,@Param("teaCrsId") String teaCrsId) throws DataAccessException;

    /**
     * 查学生的平均分，在学生基本信息里面显示
     * @param id
     * @return
     */
    Double findAvgScore(@Param("id") String id);

    /**
     * 查学生总学分，在学生基本信息里面显示
     * @param id
     * @return
     */
    Integer findSumCredit(@Param("id") String id);

    /**
     * 教师，学生成绩（教学班级名单）
     * @param tcno
     * @return
     */
    List<Score> findScoreByTC(@Param("tcno") String tcno);

    /**
     * 教师更新成绩
     * @param score
     * @return
     */
    int updateScore(Score score);
}
