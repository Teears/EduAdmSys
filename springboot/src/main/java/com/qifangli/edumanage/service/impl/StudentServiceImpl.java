package com.qifangli.edumanage.service.impl;

import com.qifangli.edumanage.dao.entity.Score;
import com.qifangli.edumanage.dao.entity.Student;
import com.qifangli.edumanage.dao.entity.StudentScore;
import com.qifangli.edumanage.dao.mapper.CourseArrangeMapper;
import com.qifangli.edumanage.dao.mapper.ScoreMapper;
import com.qifangli.edumanage.dao.mapper.StudentMapper;
import com.qifangli.edumanage.dao.mapper.StudentScoreMapper;
import com.qifangli.edumanage.service.StudentService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;

    @Resource
    private StudentScoreMapper studentScoreMapper;

    @Resource
    private ScoreMapper scoreMapper;

    @Resource
    private CourseArrangeMapper courseArrangeMapper;

    @Override
    public List<StudentScore> findScoreByTermAndStuId(String term, String id) {
        return studentScoreMapper.findScoreByTermAndStuId(term,id);
    }

    @Override
    public Student findStudentById(String id) {
        return studentMapper.findStudentById(id);
    }

    @Override
    public void addCrs(String stuId, String teaCrsId) throws DataAccessException{
        scoreMapper.addCrs(stuId,teaCrsId);
    }

    @Override
    public void deleteCrs(String stuId, String teaCrsId) throws DataAccessException{
        scoreMapper.deleteCrs(stuId,teaCrsId);
    }

    @Override
    public void updataSelectedAdd(String id) throws DataAccessException{
        courseArrangeMapper.updateSelectedAdd(id);
    }

    @Override
    public void updataSelectedSub(String id) throws DataAccessException{
        courseArrangeMapper.updateSelectedSub(id);
    }

    @Override
    public Double findAvgScore(String id) {
        return scoreMapper.findAvgScore(id);
    }

    @Override
    public Integer findSumCredit(String id) {
        return scoreMapper.findSumCredit(id);
    }

    @Override
    public void updatePwdByStuId(String id, String newPwd) {
        studentMapper.updatePwdByStuId(id,newPwd);
    }

    @Override
    public List<Student> findStuByDpt(String dpt) {
        return studentMapper.findStuByDpt(dpt);
    }

    @Override
    public Map<String, Object> addStu(List<Object> entityList) {
        int success = 0;
        List<Student> lis = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        for (Object object: entityList){
            Student student = (Student) object;
            System.out.println(student.getSex()+student.getBirth());
            int i = studentMapper.insertStu(student);
            if(i>0){
                success++;
            }else {
                lis.add(student);
            }
        }
        map.put("success",success);
        map.put("data",lis);
        return map;
    }
}
