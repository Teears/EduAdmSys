package com.qifangli.edumanage.service;

import com.qifangli.edumanage.dao.entity.Score;

public interface ScoreService {

    Score findScoreByStuId(String id);
}
