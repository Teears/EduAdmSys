package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.Term;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TermMapper {
    Integer findLatestTerm();

    Term findActiveTermByDpt(@Param("dpt") String dpt);
}
