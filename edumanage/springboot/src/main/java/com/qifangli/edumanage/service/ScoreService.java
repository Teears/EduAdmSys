package com.qifangli.edumanage.service;

import com.qifangli.edumanage.dao.entity.Score;

import java.util.List;

public interface ScoreService {

    Score findScoreByStuId(String id);

    /**
     * 教师，查询成绩表
     * @param tcno
     * @return
     */
    List<Score> findScoreByTC(String tcno);

    /**
     * 教师，更新单个成绩
     * @param score
     * @return
     */
    int updateScore(Score score);

    /**
     * 教师，更新学生成绩表
     * @param scoreList
     * @return
     */
    int updateScoreList(List<Score> scoreList);
}
