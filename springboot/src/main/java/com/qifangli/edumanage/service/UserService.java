package com.qifangli.edumanage.service;

import com.qifangli.edumanage.dao.entity.User;

import java.util.List;

public interface UserService {

    User getUser(String username);

    List<User> listUser();
}
