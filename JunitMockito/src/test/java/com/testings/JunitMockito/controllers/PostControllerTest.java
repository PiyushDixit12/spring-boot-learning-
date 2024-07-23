package com.testings.JunitMockito.controllers;

import com.testings.JunitMockito.entities.Post;
import com.testings.JunitMockito.services.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebMvcTest(PostController.class)
public class PostControllerTest {
    List<Post> posts = new ArrayList<>();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PostService postService;

    @BeforeEach
    void setUp() {
        posts = List.of(new Post(1, 1, "Post - 1", "This First Post"), new Post(2, 1, "Post - 2", "This is 2nd Post"));


    }

    @Test
    public void testFindAllPost() throws Exception {
        String jsonResponse = """
                [
                    {
                        "id":1,
                        "userId":1,
                        "title":"Post - 1",
                        "body":"This First Post"
                    },
                    {
                        "id":2,
                        "userId":1,
                        "title":"Post - 2",
                        "body":"This is 2nd Post"
                    }
                ]
                """;

        Mockito.when(postService.findAllPost()).thenReturn(posts);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/findAllPosts"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().json(jsonResponse));

        JSONAssert.assertEquals(jsonResponse, resultActions.andReturn().getResponse().getContentAsString(), false);
    }

    @Test
    public void testFindPostById() throws Exception {
        String jsonResponse = """
				
				    {
				        "id":1,
				        "userId":1,
				        "title":"Post - 1",
				        "body":"This First Post"
				    }
				
				""";
    Mockito.when(postService.findById(1)).thenReturn(Optional.of(new Post(1, 1, "Post - 1", "This First Post")));

    ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/findById/1"))
            .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().json(jsonResponse));

    JSONAssert.assertEquals(jsonResponse,resultActions.andReturn().getResponse().getContentAsString(), false);

    }

    @Test
    public void testUpdatePost() throws Exception {
        Post updatedPost = new Post(1, 1, "Updated Title", "Updated Body");
        String updatedJson = """
            {
                "id":1,
                "userId":1,
                "title":"Updated Title",
                "body":"Updated Body"
            }
            """;

        Mockito.when(postService.updatePost(Mockito.any(Post.class))).thenReturn(updatedPost);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/updatePost")
                        .contentType("application/json")
                        .content(updatedJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(updatedJson));

        JSONAssert.assertEquals(updatedJson, resultActions.andReturn().getResponse().getContentAsString(), false);
    }

    @Test
    public void testDeletePost() throws Exception {
        Mockito.doNothing().when(postService).deleteById(1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/deletePost/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(postService, Mockito.times(1)).deleteById(1);
    }

}
