package com.qifangli.edumanage.service;

import com.qifangli.edumanage.dao.entity.Score;
import com.qifangli.edumanage.dao.entity.Student;
import com.qifangli.edumanage.dao.entity.StudentScore;

import java.util.List;

public interface StudentService {

    List<StudentScore> findScoreByTermAndStuId(String term, String id);

    Student findStudentById(String id);

    void addCrs(String stuId,String teaCrsId);

    void deleteCrs(String stuId,String teaCrsId);

    void updataSelectedAdd(String id);

    void updataSelectedSub(String id);

    Double findAvgScore(String id);

    Integer findSumCredit(String id);

    void updatePwdByStuId(String id,String newPwd);

    List<Student> findStuByDpt(String dpt);

}
