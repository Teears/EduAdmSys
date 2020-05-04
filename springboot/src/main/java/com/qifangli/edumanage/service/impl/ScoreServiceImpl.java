package com.qifangli.edumanage.service.impl;

import com.qifangli.edumanage.dao.entity.Score;
import com.qifangli.edumanage.dao.mapper.ScoreMapper;
import com.qifangli.edumanage.service.ScoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ScoreServiceImpl implements ScoreService {
    @Resource
    private ScoreMapper scoreMapper;

    @Override
    public Score findScoreByStuId(String id) {
        return scoreMapper.findScoreByStuId(id);
    }
}
