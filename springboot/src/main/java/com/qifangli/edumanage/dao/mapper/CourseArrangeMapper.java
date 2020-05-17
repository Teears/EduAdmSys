package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.CourseArrange;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;

@Mapper
public interface CourseArrangeMapper {

    List<CourseArrange> findByTermAndSpot(@Param("term") String term, @Param("spot") String spot);

    List<CourseArrange> findByTermAndStuId(@Param("term") String term, @Param("id") String id);

    List<CourseArrange> findByTermAndTeaId(@Param("term") String term, @Param("id") String id);

    List<CourseArrange> findByTermDptGrade(@Param("term") String term, @Param("dpt") String dpt,@Param("grade") String grade);

    void updateSelectedAdd(@Param("id") String id) throws DataAccessException;

    void updateSelectedSub(@Param("id") String id) throws DataAccessException;

    int insertArrange(CourseArrange courseArrange) throws DuplicateKeyException;

    int deleteArrangeById(@Param("id") String id);

    int updateArrange(CourseArrange courseArrange);
}
