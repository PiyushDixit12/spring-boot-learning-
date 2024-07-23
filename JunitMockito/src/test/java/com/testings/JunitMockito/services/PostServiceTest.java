package com.testings.JunitMockito.services;

import com.testings.JunitMockito.entities.Post;
import com.testings.JunitMockito.repositories.PostRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class PostServiceTest {
    List<Post> posts = new ArrayList<Post>();

    @Mock
    private PostRepo postRepo;

    @InjectMocks
    private PostService postService;

    @BeforeEach
    void setUp() {
//        to create mock object
        MockitoAnnotations.openMocks(this);

        posts = List.of(new Post(1, 1, "Post 1", "this is body"));
    }


    @Test
    public void findAllPosts() {
        Mockito.when(postRepo.findAll()).thenReturn(posts);

        List<Post> post = postService.findAllPost();

//        checking it is not null not empty and its size
        Assertions.assertThat(post).isNotNull();
        Assertions.assertThat(post).isNotEmpty();
        Assertions.assertThat(post.size()).isEqualTo(posts.size());
        System.out.println("findAllPosts size is " + post.size());

    }

    @Test
    public void findPostById() {
//        giving what will return  when this method is call
        Mockito.when(postService.findById(1)).thenReturn(Optional.ofNullable(posts.get(0)));

        Post post = postService.findById(1).get();

        //checking it is not null and equal to given post
        Assertions.assertThat(post).isNotNull();
        Assertions.assertThat(post).isEqualTo(posts.get(0));

        Assertions.assertThat(post.title()).isEqualTo(posts.get(0).title());
        Assertions.assertThat(post.body()).isEqualTo(posts.get(0).body());


    }


    @Test
    public void updatePost() {
        Post existingPost = posts.get(0);
        Post updatedPost = new Post(1, 1, "Updated Post", "updated body");

        Mockito.when(postRepo.findById(1)).thenReturn(Optional.of(existingPost));
        Mockito.when(postRepo.save(updatedPost)).thenReturn(updatedPost);

        Post result = postService.updatePost(updatedPost);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.title()).isEqualTo("Updated Post");
        Assertions.assertThat(result.body()).isEqualTo("updated body");

        Mockito.verify(postRepo).findById(1);
        Mockito.verify(postRepo).save(updatedPost);
    }

    @Test
    public void deletePost() {
        Post postToDelete = posts.get(0);

        // Mocking findById to return the post to delete
        Mockito.when(postRepo.findById(1)).thenReturn(Optional.of(postToDelete));

        // Mocking deleteById to do nothing
        Mockito.doNothing().when(postRepo).deleteById(1);

        // Call the method under test
        postService.deleteById(1);

        // Verify that findById was called
        Mockito.verify(postRepo).findById(1);

        // Verify that deleteById was called
        Mockito.verify(postRepo).deleteById(1);
    }

    @AfterEach
    public void cleanup() {
        System.out.println("cleanup code here");
    }
}
