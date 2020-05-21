package com.qifangli.edumanage.service;

import com.qifangli.edumanage.dao.entity.CourseArrange;

import java.util.List;
import java.util.Map;

public interface CourseArrangeService {

    List<CourseArrange> findByTermAndSpot(String term, String spot);

    List<CourseArrange> findByTermAndStuId(String term,String id);

    List<CourseArrange> findByTermAndTeaId(String term,String id);

    List<CourseArrange> findByTermDptGrade(String term,String dpt,String grade);

    /**
     * 排课，表格批量添加课程安排
     * @param entityList
     * @return
     */
    Map<String,Object> addArrange(List<Object> entityList);

    int deleteArrangeById(String id);

    int updateArrange(CourseArrange courseArrange);
}
