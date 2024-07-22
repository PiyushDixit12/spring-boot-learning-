package com.example.blog_app_api.controllers;

import com.example.blog_app_api.config.AppConstants;
import com.example.blog_app_api.payloads.CommentDto;
import com.example.blog_app_api.services.CommentService;
import com.example.blog_app_api.utils.ApiResponse;
import com.example.blog_app_api.utils.PaginationResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Comment Controller",description = "This is for managing Comment in Blog Application")

public class CommentController {

    @Autowired
  private   CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> postComment(@RequestBody CommentDto commentDto, @PathVariable int postId) {
        return  new ResponseEntity<>(commentService.saveComment(commentDto, postId), HttpStatus.CREATED);
    }

//    @GetMapping("/comments")
//    public ResponseEntity<PaginationResponse<CommentDto>> getAllComments(@RequestParam(name = "pageNumber", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNumber,
//                                                         @RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
//                                                         @RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
//                                                         @RequestParam(name = "sortOrder", defaultValue = AppConstants.DEFAULT_SORT_ORDER, required = false) String sortOrder
//    ) {
//        return new ResponseEntity<>( commentService.getAllComments(pageNumber,pageSize,sortBy,sortOrder), HttpStatus.OK);
//    }

//    @GetMapping("/comments/user/{userId}")
//    public ResponseEntity<List<CommentDto>> getCommentsByUserId(@PathVariable int userId) {
//        return new ResponseEntity<>(commentService.getCommentsByUserId(userId), HttpStatus.OK);
//    }
//
//    @GetMapping("/comments/post/{postId}")
//    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable int postId) {
//        return new ResponseEntity<>(commentService.getCommentsByPostId(postId),HttpStatus.OK);
//    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable int commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment Deleted successfully",true),HttpStatus.OK);
    }

}
