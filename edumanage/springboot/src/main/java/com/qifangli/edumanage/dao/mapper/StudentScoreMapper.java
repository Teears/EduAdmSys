package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.StudentScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentScoreMapper {

    List<StudentScore> findScoreByTermAndStuId(@Param("term") String term, @Param("id") String id);

}
