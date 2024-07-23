package com.testings.JunitMockito.services;


import com.testings.JunitMockito.entities.Post;
import com.testings.JunitMockito.repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepo postRepository;

    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }

    public  void deleteById(int id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            postRepository.deleteById(id);
        }
    }
    public Post updatePost(Post post) {
        Optional<Post> existingPost = findById(post.id());
        if (existingPost.isPresent()) {
            return postRepository.save(post);
        }
        return null;
    }

}