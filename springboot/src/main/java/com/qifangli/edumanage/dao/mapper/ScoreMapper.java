package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

@Mapper
public interface ScoreMapper {

    Score findScoreByStuId(@Param("id") String id);

    void addCrs(@Param("stuId") String stuId,@Param("teaCrsId") String teaCrsId) throws DataAccessException;

    void deleteCrs(@Param("stuId") String stuId,@Param("teaCrsId") String teaCrsId) throws DataAccessException;

    Double findAvgScore(@Param("id") String id);

    Integer findSumCredit(@Param("id") String id);
}
