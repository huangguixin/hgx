package com.hgx.service;


import com.hgx.entity.User;

public interface UserServcie {

    public User selectById(Long id);

    public Integer insert(User user);

    public Integer deleteById(Long id);

    public User updateById(User user);
}
