package com.qifangli.edumanage.service;

import com.qifangli.edumanage.dao.entity.CourseArrange;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CourseArrangeService {

    List<CourseArrange> findByTermAndSpot(String term, String spot);
    List<CourseArrange> findByTermAndStuId(String term,String id);
}
