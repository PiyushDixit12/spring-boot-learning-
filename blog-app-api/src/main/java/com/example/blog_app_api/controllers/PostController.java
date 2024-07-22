package com.example.blog_app_api.controllers;

import com.example.blog_app_api.config.AppConstants;
import com.example.blog_app_api.payloads.PostDto;
import com.example.blog_app_api.services.FileService;
import com.example.blog_app_api.services.PostService;
import com.example.blog_app_api.utils.ApiResponse;
import com.example.blog_app_api.utils.PaginationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(path = "/api/")
@Tag(name = "Post Controller",description = "This is for managing Post in Blog Application")

public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @Operation(summary = "Create a new post", description = "Create a new post for a specific user in a specific category", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Post created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping(path = "/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto post,
            @PathVariable("userId") Integer userId,
            @PathVariable("categoryId") Integer categoryId) {
        return new ResponseEntity(postService.createPost(post, userId, categoryId),
                HttpStatus.CREATED);
    }


    @GetMapping(path = "/category/{categoryId}/posts")
    @Operation(summary = "Get all posts by category ID", description = "Retrieve all posts for a specific category", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved list of posts"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<PostDto>> getPostsByCategoryId(@PathVariable("categoryId") Integer categoryId) {
        return new ResponseEntity<>(postService.getPostsByCategory(categoryId), HttpStatus.OK);
    }

    @GetMapping(path = "/user/{userId}/posts")
    @Operation(summary = "Get all posts by user ID", description = "Retrieve all posts created by a specific user", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200", description = "Successfully retrieved list of posts"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401", description = "Unauthorized"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<PostDto>> getPostsByUserId(@PathVariable("userId") Integer userId) {
        return new ResponseEntity<>(postService.getPostsByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/posts")
    @Operation(summary = "Get all posts", description = "Retrieve all posts with pagination and sorting options", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200", description = "Successfully retrieved list of posts"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401", description = "Unauthorized"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<PaginationResponse<PostDto>> getPosts(@RequestParam(name = "pageNumber", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNumber,
                                                                @RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
                                                                @RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                                                @RequestParam(name = "sortOrder", defaultValue = AppConstants.DEFAULT_SORT_ORDER, required = false) String sortOrder
    ) {
        return new ResponseEntity<>(postService.getAllPosts(pageNumber, pageSize, sortBy, sortOrder), HttpStatus.OK);
    }

    @GetMapping("/posts/{id}")
    @Operation(summary = "Get post by ID", description = "Retrieve a specific post by its ID", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200", description = "Successfully retrieved the post"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401", description = "Unauthorized"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404", description = "Post not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @Operation(summary = "Delete post by ID", description = "Delete a specific post by its ID", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200", description = "Post deleted successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401", description = "Unauthorized"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404", description = "Post not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("id") Integer id) {
        postService.deletePost(id);
        return new ResponseEntity<>(new ApiResponse("Post Delelted Successfully", true), HttpStatus.OK);
    }

    @Operation(summary = "Update post by ID", description = "Update a specific post by its ID", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200", description = "Post updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401", description = "Unauthorized"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404", description = "Post not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto post, @PathVariable("postId") Integer postId) {
        return new ResponseEntity<>(postService.updatePost(post, postId), HttpStatus.OK);
    }

    @Operation(summary = "Search posts by title", description = "Search posts by title containing specified keywords", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200", description = "Successfully retrieved list of posts"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401", description = "Unauthorized"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostsByTitle(@PathVariable("keywords") String title) {
        return new ResponseEntity<>(postService.searchPosts(title), HttpStatus.OK);
    }


//    File Service
    @Operation(summary = "Upload an image for a post", description = "Upload an image for a specific post", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200", description = "Image uploaded successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400", description = "Bad Request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401", description = "Unauthorized"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404", description = "Post not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/posts/image/upload/{postId}")
    public ResponseEntity<String> uploadFileImage(@RequestParam("image") MultipartFile file, @PathVariable int postId) throws IOException {

        Optional<PostDto> postOptional = Optional.ofNullable(postService.getPostById(postId));
        String fileName = fileService.uploadFile(path, file);
        if (postOptional.isPresent()) {
            postOptional.get().setImageName(fileName);
            postService.updatePost(postOptional.get(), postId);
        }
        return new ResponseEntity<>("Image Uploaded Successfully on path " + fileName, HttpStatus.OK);
    }
    @Operation(summary = "Get image for a post", description = "Retrieve the image for a specific post", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200", description = "Successfully retrieved the image"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401", description = "Unauthorized"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404", description = "Post or image not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500", description = "Internal server error")
    })
    @GetMapping(path = "/posts/image/{postId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void getFileImage(@PathVariable("postId") Integer postId, HttpServletResponse response) throws IOException {
        PostDto postDto = postService.getPostById(postId);
        InputStream io = fileService.getResource(path, postDto.getImageName());

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(io, response.getOutputStream());
    }
}
