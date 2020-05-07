package com.qifangli.edumanage.service.impl;

import com.qifangli.edumanage.dao.entity.Teacher;
import com.qifangli.edumanage.dao.mapper.TeacherMapper;
import com.qifangli.edumanage.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public Teacher findTeacherById(String id) {
        return teacherMapper.findTeacherById(id);
    }
}
