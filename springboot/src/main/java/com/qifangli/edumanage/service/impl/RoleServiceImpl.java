package com.qifangli.edumanage.service.impl;

import com.qifangli.edumanage.dao.entity.Role;
import com.qifangli.edumanage.dao.mapper.RoleMapper;
import com.qifangli.edumanage.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public Role findRoleByUserId(String id) {
        return roleMapper.findRoleByUserId(id);
    }
}
