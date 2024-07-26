package com.redis.ApiRedis.doa;

import com.redis.ApiRedis.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDao {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String KEY = "USER";

    public User saveUser(User user) {
        redisTemplate.opsForHash().put(KEY, user.getUserId(), user);
        return user;
    }

    public User findUserByUserId(String userId) {
        return (User) redisTemplate.opsForHash().get(KEY, userId);
    }

    public Map<Object,Object> findAllUsers() {
      return  redisTemplate.opsForHash().entries(KEY);
    }

    public void deleteUserByUserId(String userId) {
        redisTemplate.opsForHash().delete(KEY, userId);
    }


}
