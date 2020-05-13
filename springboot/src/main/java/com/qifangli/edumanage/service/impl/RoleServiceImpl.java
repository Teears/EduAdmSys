package com.qifangli.edumanage.service.impl;

import com.qifangli.edumanage.dao.entity.Role;
import com.qifangli.edumanage.dao.mapper.RoleMapper;
import com.qifangli.edumanage.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public Role findRoleByUserId(String id) {
        return roleMapper.findRoleByUserId(id);
    }

    @Override
    public List<Role> findAllRole(String id) {
        return roleMapper.findAllRole(id);
    }

    @Override
    public void updateToSuperAdminById(String id) {
        roleMapper.updateToSuperAdminById(id);
    }

    @Override
    public void updateToAdminById(String id) {
        roleMapper.updateToAdminById(id);
    }

    @Override
    public void deleteByUid(String id) {
        roleMapper.deleteByUid(id);
    }

    @Override
    public void addAdmin(String uid, String rid) {
        roleMapper.addAdmin(uid,rid);
    }
}
