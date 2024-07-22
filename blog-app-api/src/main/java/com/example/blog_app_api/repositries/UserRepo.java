package com.example.blog_app_api.repositries;

import com.example.blog_app_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
Optional<User> findByName(String username);
}
