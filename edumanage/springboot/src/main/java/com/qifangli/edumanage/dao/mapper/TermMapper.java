package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.Term;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

@Mapper
public interface TermMapper {

    Integer findLatestTerm();

    Term findActiveTerm();

    int insertTerm(Term term) throws DataAccessException;

    void updateActiveTerm(String name);
}
