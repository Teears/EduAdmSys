package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleMapper {

    Role findRoleByUserId(@Param("id") String id);
}