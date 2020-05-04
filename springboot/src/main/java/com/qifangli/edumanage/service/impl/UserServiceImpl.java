package com.qifangli.edumanage.service.impl;

import com.qifangli.edumanage.dao.entity.User;
import com.qifangli.edumanage.dao.mapper.UserMapper;
import com.qifangli.edumanage.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: UserServiceImpl
 * @Description:
 * @Author 刘仁
 * @DateTime 2019年4月1日 下午5:02:16
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUser(String username) {
        return userMapper.getUser(username);
    }

    @Override
    public List<User> listUser() {
        return userMapper.listUser();
    }

}
