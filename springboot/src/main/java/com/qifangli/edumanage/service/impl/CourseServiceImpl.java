package com.qifangli.edumanage.service.impl;

import com.qifangli.edumanage.dao.entity.Course;
import com.qifangli.edumanage.dao.mapper.CourseMapper;
import com.qifangli.edumanage.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseMapper courseMapper;

    @Override
    public Course findCourseById(String id) {
        return courseMapper.findCourseById(id);
    }
}
