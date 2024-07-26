package com.redis.ApiRedis.controller;

import com.redis.ApiRedis.doa.UserDao;
import com.redis.ApiRedis.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserDao userDao;

    @PostMapping
    public User save(@RequestBody User user) {
        return userDao.saveUser(user);
    }

    @GetMapping
    public Map<Object, Object> findAll() {
        return userDao.findAllUsers();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable String id) {
        return userDao.findUserByUserId(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        userDao.deleteUserByUserId(id);
        return "User deleted successfully with id : "+id;
    }

}
