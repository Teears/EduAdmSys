package com.qifangli.edumanage.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassAndGradeMapper {

    List<String> findAllClass();
}
