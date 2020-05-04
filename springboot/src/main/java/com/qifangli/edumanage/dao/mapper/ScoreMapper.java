package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ScoreMapper {

    Score findScoreByStuId(@Param("id") String id);
}
