package com.qifangli.edumanage.service;

import com.qifangli.edumanage.dao.entity.Course;
import org.apache.ibatis.annotations.Param;

public interface CourseService {

    Course findCourseById(String id);
}
