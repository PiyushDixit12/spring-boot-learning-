package com.example.blog_app_api.repositries;

import com.example.blog_app_api.entities.Category;
import com.example.blog_app_api.entities.Post;
import com.example.blog_app_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);
}
