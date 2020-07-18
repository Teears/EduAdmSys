package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {

    Role findRoleByUserId(@Param("id") String id);

    List<Role> findAllRole(@Param("id") String id);

    void updateToSuperAdminById(@Param("id") String id);

    void updateToAdminById(@Param("id") String id);

    void deleteByUid(@Param("id") String id);

    void addAdmin(@Param("uid") String uid,@Param("rid") String rid);
}