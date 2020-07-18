package com.qifangli.edumanage.service.impl;

import com.qifangli.edumanage.dao.entity.Department;
import com.qifangli.edumanage.dao.mapper.DepartmentMapper;
import com.qifangli.edumanage.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public Department findDptByid(String id) {
        return departmentMapper.findDptByid(id);
    }

    @Override
    public List<Department> findAllDpt() {
        return departmentMapper.findAllDpt();
    }
}
