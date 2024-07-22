package com.example.blog_app_api.repositries;

import com.example.blog_app_api.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
    public List<Comment> findByPostId(int postId);
//    public List<Comment> findByUserId(int userId);
}
