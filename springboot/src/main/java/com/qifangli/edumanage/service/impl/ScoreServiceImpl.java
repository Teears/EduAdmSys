package com.qifangli.edumanage.service.impl;

import com.qifangli.edumanage.dao.entity.Score;
import com.qifangli.edumanage.dao.mapper.ScoreMapper;
import com.qifangli.edumanage.service.ScoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {
    @Resource
    private ScoreMapper scoreMapper;

    @Override
    public Score findScoreByStuId(String id) {
        return scoreMapper.findScoreByStuId(id);
    }

    @Override
    public List<Score> findScoreByTC(String tcno) {
        return scoreMapper.findScoreByTC(tcno);
    }

    @Override
    public int updateScore(Score score) {
        return scoreMapper.updateScore(score);
    }

    @Override
    public int updateScoreList(List<Score> scoreList) {
        int success = 0;
        for(Score score: scoreList){
            try {
                if(scoreMapper.updateScore(score)>0){
                    success++;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return success;
    }
}
