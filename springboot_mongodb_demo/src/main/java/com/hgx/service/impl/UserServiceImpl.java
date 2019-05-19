package com.hgx.service.impl;

import com.hgx.entity.User;
import com.hgx.repository.BaseRepository;
import com.hgx.repository.UserRepository;
import com.hgx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author :huangguixin / 2677540604@qq.com
 * @version : 1.0
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected BaseRepository getDao() {
        return this.userRepository;
    }
}
