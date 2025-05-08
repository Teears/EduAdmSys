package com.qifangli.edumanage.service;

import com.qifangli.edumanage.dao.entity.Department;

import java.util.List;

public interface DepartmentService {

    Department findDptByid(String id);

    List<Department> findAllDpt();

}
