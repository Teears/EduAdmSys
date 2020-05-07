package com.qifangli.edumanage.service.impl;

import com.qifangli.edumanage.dao.entity.Student;
import com.qifangli.edumanage.dao.entity.StudentScore;
import com.qifangli.edumanage.dao.mapper.StudentMapper;
import com.qifangli.edumanage.dao.mapper.StudentScoreMapper;
import com.qifangli.edumanage.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
//    @Autowired
    @Resource
    private StudentMapper studentMapper;

    @Resource
    private StudentScoreMapper studentScoreMapper;

    @Override
    public List<StudentScore> findScoreByTermAndStuId(String term, String id) {
        return studentScoreMapper.findScoreByTermAndStuId(term,id);
    }

    @Override
    public Student findStudentById(String id) {
        return studentMapper.findStudentById(id);
    }
}
