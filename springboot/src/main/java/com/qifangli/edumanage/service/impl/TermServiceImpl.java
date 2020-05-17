package com.qifangli.edumanage.service.impl;

import com.qifangli.edumanage.dao.entity.Term;
import com.qifangli.edumanage.dao.mapper.TermMapper;
import com.qifangli.edumanage.service.TermService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TermServiceImpl implements TermService {

    @Resource
    private TermMapper termMapper;

    @Override
    public Integer findLatestTerm() {
        return termMapper.findLatestTerm();
    }

    @Override
    public Term findActiveTermByDpt(String dpt) {
        return termMapper.findActiveTermByDpt(dpt);
    }
}
