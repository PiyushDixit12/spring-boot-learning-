package com.testings.JunitMockito.controllers;

import com.testings.JunitMockito.entities.Post;
import com.testings.JunitMockito.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/findAllPosts")
    public List<Post> findAllPosts() {
        return postService.findAllPost();
    }

    @GetMapping("/findById/{id}")
    public Post findById(@PathVariable int id) {
        return postService.findById(id).get();
    }

    @DeleteMapping("/deletePost/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable int id) {
        postService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updatePost")
    public ResponseEntity<Post> updatePost(@RequestBody Post post) {
        Post updatedPost = postService.updatePost(post);
        return ResponseEntity.ok(updatedPost);
    }

}