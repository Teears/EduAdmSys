package com.qifangli.edumanage.service.impl;

import com.qifangli.edumanage.dao.mapper.ClassAndGradeMapper;
import com.qifangli.edumanage.service.ClassAndGradeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClassAndGradeServiceImpl implements ClassAndGradeService {
    @Resource
    private ClassAndGradeMapper classAndGradeMapper;

    @Override
    public List<String> findAllClass() {
        return classAndGradeMapper.findAllClass();
    }
}
