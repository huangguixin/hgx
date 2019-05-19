package com.hgx.controller;

import com.hgx.entity.User;
import com.hgx.service.UserService;
import com.hgx.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author :huangguixin / 2677540604@qq.com
 * @version : 1.0
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private IdWorker idWorker;

    @PostMapping
    public User add(User user) {
        if (user.getId() == null) {
            user.setId(idWorker.nextId());
        }
        return (User) userService.save(user);
    }

    @PostMapping("addList")
    public List<User> add(@RequestBody List<User> users) {
        users.stream().forEach((user) -> {
            if (user.getId() == null) {
                user.setId(idWorker.nextId());
            }
        });
        return userService.saveAll(users);
    }

    @GetMapping("{id}")
    public User get(@PathVariable Long id) {
        return (User) userService.findById(id);
    }

    @PutMapping
    public User update(User user) {
        return (User) userService.save(user);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteById(id);
    }

}
