package com.qifangli.edumanage.service;

import com.qifangli.edumanage.dao.entity.Role;

import java.util.List;

public interface RoleService {

    Role findRoleByUserId(String id);

    List<Role> findAllRole(String id);

    void updateToSuperAdminById(String id);

    void updateToAdminById(String id);

    void deleteByUid(String id);

    void addAdmin(String uid,String rid);


}
