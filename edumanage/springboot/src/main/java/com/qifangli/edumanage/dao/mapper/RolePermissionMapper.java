package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RolePermissionMapper {

    RolePermission findStuSelect();

    void addStuSelect();

    void deleteStuSelect();
}
