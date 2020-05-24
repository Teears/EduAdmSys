package com.qifangli.edumanage.service;

import com.qifangli.edumanage.dao.entity.StudentScore;
import com.qifangli.edumanage.dao.entity.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {

    List<StudentScore> findScoreByTermAndStuId(String term, String id);

    Student findStudentById(String id);

    void addCrs(String stuId,String teaCrsId);

    void deleteCrs(String stuId,String teaCrsId);

    void updataSelectedAdd(String id);

    void updataSelectedSub(String id);

    Double findAvgScore(String id);

    Integer findSumCredit(String id);

    int updatePwdByStuId(String id,String newPwd);

    List<Student> findStuByDptAndGrade(String dpt,String grade);

    Map<String,Object> addStu(List<Object> entityList);

    int insertStu(Student student);

    int updateStu(Student student);

    int deleteStuById(String id);

    int updateStuTel(String id, String telephone);
}
