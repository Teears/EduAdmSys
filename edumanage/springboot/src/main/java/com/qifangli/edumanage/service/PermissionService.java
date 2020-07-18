package com.qifangli.edumanage.service;

import com.qifangli.edumanage.dao.entity.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> findPermsByRid(String rid);
}
