package com.qifangli.edumanage.service;

import com.qifangli.edumanage.dao.entity.Score;

import java.util.List;

public interface ScoreService {

    Score findScoreByStuId(String id);

    List<Score> findScoreByTC(String tcno);

    int updateScore(Score score);

    int updateScoreList(List<Score> scoreList);
}
