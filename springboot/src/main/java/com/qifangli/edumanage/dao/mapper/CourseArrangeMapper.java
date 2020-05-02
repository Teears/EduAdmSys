package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.CourseArrange;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseArrangeMapper {

    public List<CourseArrange> findByTermAndSpot(@Param("term") String term, @Param("spot") String spot);
}
