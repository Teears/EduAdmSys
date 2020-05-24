package com.qifangli.edumanage.service.impl;

import com.qifangli.edumanage.dao.entity.StudentScore;
import com.qifangli.edumanage.dao.mapper.CourseArrangeMapper;
import com.qifangli.edumanage.dao.mapper.StudentMapper;
import com.qifangli.edumanage.dao.mapper.StudentScoreMapper;
import com.qifangli.edumanage.service.StudentService;
import com.qifangli.edumanage.dao.entity.Student;
import com.qifangli.edumanage.dao.mapper.ScoreMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
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
    public int updatePwdByStuId(String id, String newPwd) {
        return studentMapper.updatePwdByStuId(id,newPwd);
    }

    @Override
    public List<Student> findStuByDptAndGrade(String dpt, String grade) {
        return studentMapper.findStuByDptAndGrade(dpt,grade);
    }

    @Override
    public Map<String, Object> addStu(List<Object> entityList) {
        int success = 0;
        List<Student> lis = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        for (Object object: entityList){
            Student student = (Student) object;
            try {
                int i = studentMapper.insertStu(student);
                if(i>0){
                    success++;
                }else {
                    lis.add(student);
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
    public int insertStu(Student student) {
        return studentMapper.insertStu(student);
    }

    @Override
    public int updateStu(Student student) {
        return studentMapper.updateStu(student);
    }

    @Override
    public int deleteStuById(String id) {
        return studentMapper.deleteStuById(id);
    }

    @Override
    public int updateStuTel(String id, String telephone) {
        return studentMapper.updateStuTel(id,telephone);
    }
}
