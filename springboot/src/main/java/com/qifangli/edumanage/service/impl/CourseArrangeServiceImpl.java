package com.qifangli.edumanage.service.impl;

import com.qifangli.edumanage.dao.entity.CourseArrange;
import com.qifangli.edumanage.dao.mapper.CourseArrangeMapper;
import com.qifangli.edumanage.service.CourseArrangeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseArrangeServiceImpl implements CourseArrangeService {
    @Resource
    private CourseArrangeMapper courseArrangeMapper;

    @Override
    public List<CourseArrange> findByTermAndSpot(String term, String spot) {
        return courseArrangeMapper.findByTermAndSpot(term, spot);
    }

    @Override
    public List<CourseArrange> findByTermAndStuId(String term, String id) {
        return courseArrangeMapper.findByTermAndStuId(term,id);
    }
}
