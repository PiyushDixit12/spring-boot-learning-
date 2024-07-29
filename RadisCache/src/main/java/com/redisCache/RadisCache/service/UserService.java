package com.redisCache.RadisCache.service;

import com.redisCache.RadisCache.entity.User;
import com.redisCache.RadisCache.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepository;

    @Cacheable(value = "users", key = "#id")
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @CachePut(value = "users", key = "#user.id")
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @CacheEvict(value = "users",key = "#id")
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
