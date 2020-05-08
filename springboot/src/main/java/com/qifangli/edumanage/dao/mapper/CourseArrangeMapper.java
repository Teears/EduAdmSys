package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.CourseArrange;
import com.qifangli.edumanage.dao.entity.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface CourseArrangeMapper {

    List<CourseArrange> findByTermAndSpot(@Param("term") String term, @Param("spot") String spot);

    List<CourseArrange> findByTermAndStuId(@Param("term") String term, @Param("id") String id);

    List<CourseArrange> findByTermAndDpt(@Param("term") String term, @Param("dpt") String dpt);

    void updateSelectedAdd(@Param("id") String id) throws DataAccessException;

    void updateSelectedSub(@Param("id") String id) throws DataAccessException;

}
