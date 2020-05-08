package com.qifangli.edumanage.service;

import com.qifangli.edumanage.dao.entity.Role;

public interface RoleService {

    Role findRoleByUserId(String id);
}
