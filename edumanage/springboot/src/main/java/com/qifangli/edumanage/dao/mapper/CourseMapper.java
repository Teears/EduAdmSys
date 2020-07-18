package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;

@Mapper
public interface CourseMapper {

    Course findCourseById(@Param("id") String id);

    List<Course> findCourseByDpt(@Param("dpt") String dpt);

    int deleteCrsById(@Param("id") String id);

    int updateCrs(Course course);

    int insertCrs(Course course) throws DuplicateKeyException;
}
