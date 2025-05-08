package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    Department findDptByid(@Param("id") String id);

    List<Department> findAllDpt();
}
