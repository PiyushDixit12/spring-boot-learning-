package com.example.blog_app_api.services;

import com.example.blog_app_api.entities.Post;
import com.example.blog_app_api.payloads.PostDto;
import com.example.blog_app_api.utils.PaginationResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
    PaginationResponse<PostDto> getAllPosts(Integer pageNumber, Integer pageSize, String sortBy,String sortOrder);
    PostDto getPostById(int id);
    PostDto updatePost( PostDto postDto ,int postId);
    void deletePost(int id);

    List<PostDto> getPostsByCategory(Integer categoryId);
    List<PostDto> getPostsByUser(Integer userId);

    List<PostDto> searchPosts(String keyword);
}
