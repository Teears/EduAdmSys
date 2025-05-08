package com.qifangli.edumanage.service.impl;

import com.qifangli.edumanage.dao.mapper.CourseMapper;
import com.qifangli.edumanage.service.CourseService;
import com.qifangli.edumanage.dao.entity.Course;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseMapper courseMapper;

    @Override
    public Course findCourseById(String id) {
        return courseMapper.findCourseById(id);
    }

    @Override
    public List<Course> findCourseByDpt(String dpt) {
        return courseMapper.findCourseByDpt(dpt);
    }

    @Override
    public int deleteCrsById(String id) {
        return courseMapper.deleteCrsById(id);
    }

    @Override
    public int updateCrs(Course course) {
        return courseMapper.updateCrs(course);
    }

    @Override
    public int insertCrs(Course course) throws DuplicateKeyException {
        return courseMapper.insertCrs(course);
    }

    @Override
    public Map<String, Object> addCrs(List<Object> entityList) {
        int success = 0;
        List<Course> lis = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        for (Object object: entityList){
            Course course = (Course) object;
            System.out.println(course.getDepartment());
            try {
                int i = courseMapper.insertCrs(course);
                if(i>0){
                    success++;
                }else {
                    lis.add(course);
                }
            }catch (DuplicateKeyException e){
                System.out.println("违反唯一性约束：学号存在重复");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        map.put("success",success);
        map.put("data",lis);
        return map;
    }
}
