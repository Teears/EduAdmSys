package com.qifangli.edumanage.service;

import com.qifangli.edumanage.dao.entity.Term;

public interface TermService {
    Integer findLatestTerm();

    Term findActiveTermByDpt(String dpt);
}
