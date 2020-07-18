package com.qifangli.edumanage.service.impl;

import com.qifangli.edumanage.dao.entity.RolePermission;
import com.qifangli.edumanage.dao.mapper.RolePermissionMapper;
import com.qifangli.edumanage.service.RolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Resource
    RolePermissionMapper rolePermissionMapper;

    @Override
    public RolePermission findStuSelect() {
        return rolePermissionMapper.findStuSelect();
    }

    @Override
    public void addStuSelect() {
        rolePermissionMapper.addStuSelect();
    }

    @Override
    public void deleteStuSelect() {
        rolePermissionMapper.deleteStuSelect();
    }
}
