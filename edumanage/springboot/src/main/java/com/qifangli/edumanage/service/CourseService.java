package com.qifangli.edumanage.service;

import com.qifangli.edumanage.dao.entity.Course;

import java.util.List;
import java.util.Map;

public interface CourseService {

    Course findCourseById(String id);

    List<Course> findCourseByDpt(String dpt);

    int deleteCrsById(String id);

    int updateCrs(Course course);

    int insertCrs(Course course);

    Map<String,Object> addCrs(List<Object> entityList);
}
