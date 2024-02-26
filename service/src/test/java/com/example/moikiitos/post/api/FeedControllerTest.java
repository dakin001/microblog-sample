package com.example.moikiitos.post.api;

import com.example.moikiitos.domain.post.api.FeedController;
import com.example.moikiitos.domain.post.model.FeedQueryDto;
import com.example.moikiitos.domain.post.model.Post;
import com.example.moikiitos.domain.post.service.FeedService;
import com.example.moikiitos.domain.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class FeedControllerTest {
    @Mock
    FeedService feedService;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        FeedController controller = new FeedController(feedService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void queryUserFeed_has1Records_Return1Post() throws Exception {
        User user = new User();
        user.setName("user1");
        user.setEmail("user1@example.com");
        Post post = new Post();
        post.setUser(user);
        post.setContent("Hello 2024");

        when(feedService.queryUserFeed(any(FeedQueryDto.class))).thenReturn(List.of(post));

        this.mockMvc.perform(get("/feed/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].content").value(post.getContent()))
                .andExpect(jsonPath("$[0].user.name").value(user.getName()));
    }
}