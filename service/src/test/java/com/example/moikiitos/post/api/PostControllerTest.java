package com.example.moikiitos.post.api;

import com.example.moikiitos.domain.post.api.PostController;
import com.example.moikiitos.domain.post.model.PostCreateDto;
import com.example.moikiitos.domain.post.service.PostService;
import com.example.moikiitos.domain.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PostControllerTest {
    @Mock
    PostService postService;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        PostController controller = new PostController(postService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void createPost_normalContent_success() throws Exception {
        String json = """ 
                {
                  "content": "Hello blogger!"
                }
                """;

        this.mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        verify(postService, times(1)).createPost(any(), any(PostCreateDto.class));
    }

    @Test
    void createPost_bigContent_fail() throws Exception {
        String json = """ 
                {
                  "content": "long content $content"
                }
                """;
        json = json.replace("$content", "a".repeat(2000));

        this.mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());

        verify(postService, never()).createPost(any(User.class), any(PostCreateDto.class));
    }
}