package com.qifangli.edumanage.service.impl;

import com.qifangli.edumanage.dao.entity.Teacher;
import com.qifangli.edumanage.dao.mapper.TeacherMapper;
import com.qifangli.edumanage.service.TeacherService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public Teacher findTeacherById(String id) {
        return teacherMapper.findTeacherById(id);
    }

    @Override
    public int updatePwdByTeaId(String id, String newPwd) {
        return teacherMapper.updatePwdByTeaId(id,newPwd);
    }

    @Override
    public List<Teacher> findTeacherByDpt(String dpt) {
        return teacherMapper.findTeacherByDpt(dpt);
    }

    @Override
    public Map<String, Object> addTea(List<Object> entityList) {
        int success = 0;
        List<Teacher> lis = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        for (Object object: entityList){
            Teacher teacher = (Teacher) object;
            System.out.println(teacher.getBirth());
            try {
                int i = teacherMapper.insertTea(teacher);
                if(i>0){
                    success++;
                }else {
                    lis.add(teacher);
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

    @Override
    public int insertTea(Teacher teacher) {
        return teacherMapper.insertTea(teacher);
    }

    @Override
    public int updateTea(Teacher teacher) {
        return teacherMapper.updateTea(teacher);
    }

    @Override
    public int deleteTeaById(String id) {
        return teacherMapper.deleteTeaById(id);
    }

    @Override
    public int updateTeaTel(String id, String telephone) {
        return teacherMapper.updateTeaTel(id,telephone);
    }
}
