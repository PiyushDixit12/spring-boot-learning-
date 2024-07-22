package com.example.blog_app_api.services;

import com.example.blog_app_api.entities.Comment;
import com.example.blog_app_api.entities.Post;
import com.example.blog_app_api.entities.User;
import com.example.blog_app_api.exceptions.ResourceNotFoundException;
import com.example.blog_app_api.payloads.CommentDto;
import com.example.blog_app_api.payloads.PostDto;
import com.example.blog_app_api.payloads.UserDto;
import com.example.blog_app_api.repositries.CommentRepo;
import com.example.blog_app_api.repositries.PostRepo;
import com.example.blog_app_api.repositries.UserRepo;
import com.example.blog_app_api.utils.PaginationResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserRepo userRepo;

//    @Autowired
//    PostService postService;
//    @Autowired
//    UserService userService;

    @Override
    public CommentDto saveComment(CommentDto comment, int postId) {
//        Optional<User> user = Optional.ofNullable(userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId)));
        Optional<Post> post = Optional.ofNullable(postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId)));

        Comment commentNew = modelMapper.map(comment, Comment.class);
//        commentNew.setUser(user.get());
        commentNew.setPost(post.get());
        return modelMapper.map(commentRepo.save(commentNew), CommentDto.class);
    }

//    @Override
//    public PaginationResponse<CommentDto> getAllComments(int pageNumber, int pageSize, String sortBy, String sortOrder) {
//        Pageable p = null;
//
//        if (sortOrder.equalsIgnoreCase("asc")) {
//            p = (Pageable) PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
//        } else {
//            p = (Pageable) PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
//        }
//        Page<Comment> page = commentRepo.findAll(p);
//        List<CommentDto> commentDtos = page.getContent().stream().map(comment -> modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
//        PaginationResponse<CommentDto> commentDtoPaginationResponse = new PaginationResponse<CommentDto>();
//
//        commentDtoPaginationResponse.setTotalPages(page.getTotalPages());
//        commentDtoPaginationResponse.setPageNumber(page.getNumber());
//        commentDtoPaginationResponse.setPageSize(page.getSize());
//        commentDtoPaginationResponse.setHasNextPage(page.hasNext());
//        commentDtoPaginationResponse.setTotalElements(page.getNumberOfElements());
//        commentDtoPaginationResponse.setContent(commentDtos);
//
//        return commentDtoPaginationResponse;
//    }
//
//    @Override
//    public CommentDto getComment(int commentId) {
//        Optional<Comment> comment = Optional.ofNullable(commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId)));
//        return modelMapper.map(comment.get(), CommentDto.class);
//    }

    @Override
    public void deleteComment(int commentId) {
        Optional<Comment> comment = Optional.ofNullable(commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId)));
        if (comment.isPresent()) {
            commentRepo.delete(comment.get());
        }
    }

//    @Override
//    public List<CommentDto> getCommentsByPostId(int postId) {
//        Optional<Post> post = Optional.ofNullable(postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId)));
////        PostDto post = postService.getPostById(postId);
//        return commentRepo.findByPostId(postId).stream().map((comment) -> modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
//    }
//
//    @Override
//    public List<CommentDto> getCommentsByUserId(int userId) {
//return new ArrayList<>();
////        return  commentRepo.findByUserId(userId).stream().map((comment) -> modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
//    }


}
