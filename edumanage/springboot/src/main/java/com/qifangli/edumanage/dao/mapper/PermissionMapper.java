package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionMapper {

     List<Permission> findPermsByRid(@Param("rid") String rid);
}
