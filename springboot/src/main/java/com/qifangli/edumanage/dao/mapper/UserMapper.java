package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


    User getUser(String username);


    List<User> listUser();
}
