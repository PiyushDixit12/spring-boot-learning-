package com.example.blog_app_api.services;

import com.example.blog_app_api.entities.Category;
import com.example.blog_app_api.entities.Post;
import com.example.blog_app_api.entities.User;
import com.example.blog_app_api.exceptions.ResourceNotFoundException;
import com.example.blog_app_api.payloads.PostDto;
import com.example.blog_app_api.repositries.CategoryRepo;
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

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        Optional<User> userOptional = Optional.ofNullable(userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId)));
        Optional<Category> categoryOptional = Optional.ofNullable(categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId)));

        if (userOptional.isPresent() && categoryOptional.isPresent()) {

            Post post = modelMapper.map(postDto, Post.class);
            post.setImageName("default.png");
            post.setAddedDate(new Date());
            post.setUser(userOptional.get());
            post.setCategory(categoryOptional.get());
            return modelMapper.map(postRepo.save(post), PostDto.class);
        }
        return null;
    }

    @Override
    public PaginationResponse<PostDto> getAllPosts(Integer pageNumber, Integer pageSize, String sortBy,String sortOrder) {


        Pageable p ;
        if (sortOrder.equalsIgnoreCase("asc")) {
            p =PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        }else {
            p =PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        }

        Page<Post> posts = postRepo.findAll(p);

        PaginationResponse<PostDto> paginationResponse = new PaginationResponse<PostDto>();

        paginationResponse.setTotalPages(posts.getTotalPages());
        paginationResponse.setContent(posts.getContent().stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList()));
        paginationResponse.setHasNextPage(posts.hasNext());
        paginationResponse.setTotalElements(posts.getNumberOfElements());
        paginationResponse.setPageNumber(posts.getNumber());
        paginationResponse.setPageSize(posts.getSize());
        paginationResponse.setTotalPages(posts.getTotalPages());
        return paginationResponse;
    }

    @Override
    public PostDto getPostById(int id) {
        Optional<Post> postOptional = Optional.ofNullable(postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id)));
        return modelMapper.map(postOptional.get(), PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, int postId) {
        Optional<Post> post = Optional.ofNullable(postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId)));
        if (post.isPresent()) {
            Post updatedPost = modelMapper.map(post.get(), Post.class);
            if (postDto.getImageName() != null && !postDto.getImageName().trim().isEmpty()) {
                updatedPost.setImageName(postDto.getImageName());
            }
            if (postDto.getTitle() != null && !postDto.getTitle().trim().isEmpty()) {
                updatedPost.setTitle(postDto.getTitle());
            }
            if (postDto.getContent() != null && !postDto.getContent().trim().isEmpty()) {
                updatedPost.setContent(postDto.getContent());
            }
            return modelMapper.map(postRepo.save(updatedPost), PostDto.class);
        }

        return null;
    }

    @Override
    public void deletePost(int id) {
        Optional<Post> post = Optional.ofNullable(postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id)));
        postRepo.delete(post.get());
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Optional<Category> category = Optional.ofNullable(categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId)));
        List<Post> posts = postRepo.findByCategory(category.get());

        return posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        Optional<User> user = Optional.ofNullable(userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId)));
        List<Post> posts = postRepo.findByUser(user.get());
        return posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
   return  postRepo.findByTitleContaining(keyword).stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }
}
