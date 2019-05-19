package com.hgx.controller;

import com.hgx.entity.User;
import com.hgx.service.UserServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserServcie userServcie;

    @PostMapping
    public Integer insert(@RequestBody User user) {
        return userServcie.insert(user);
    }

    @GetMapping("{id}")
    public User selectById(@PathVariable("id") Long id) {
        User user = userServcie.selectById(id);
        System.out.println(id);
        System.out.println(user);
        return user;
    }

    @DeleteMapping("{id}")
    public Integer deleteById(@PathVariable("id") Long id){
        System.out.println(id);
        return userServcie.deleteById(id);
    }

    @PutMapping
    public User updateById(@RequestBody User user){
        return userServcie.updateById(user);
    }

}
