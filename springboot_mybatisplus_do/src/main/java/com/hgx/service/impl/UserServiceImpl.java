package com.hgx.service.impl;

import com.hgx.entity.User;
import com.hgx.mapper.UserMapper;
import com.hgx.service.UserServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServcie {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public Integer insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public Integer deleteById(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public User updateById(User user) {
        userMapper.updateById(user);
        return user;
    }

}
