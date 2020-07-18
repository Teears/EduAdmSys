package com.qifangli.edumanage.service.impl;

import com.qifangli.edumanage.dao.entity.Permission;
import com.qifangli.edumanage.dao.mapper.PermissionMapper;
import com.qifangli.edumanage.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findPermsByRid(String rid) {
        return permissionMapper.findPermsByRid(rid);
    }
}
