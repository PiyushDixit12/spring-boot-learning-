package com.example.blog_app_api.services;

import com.example.blog_app_api.payloads.CommentDto;
import com.example.blog_app_api.utils.PaginationResponse;

import java.util.List;

public interface CommentService {

    public CommentDto saveComment(CommentDto comment,int postId);
//    public PaginationResponse<CommentDto> getAllComments(int pageNumber , int pageSize , String sortBy, String sortOrder);
//    public CommentDto getComment(int commentId);
    public void deleteComment(int commentId);
//    public List<CommentDto> getCommentsByPostId(int postId);
//    public List<CommentDto> getCommentsByUserId(int userId);
}
