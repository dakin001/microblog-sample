package com.example.moikiitos.user.api;

import com.example.moikiitos.account.service.AuthService;
import com.example.moikiitos.user.model.User;
import com.example.moikiitos.user.model.UserFollowQueryDto;
import com.example.moikiitos.user.model.UserQueryDto;
import com.example.moikiitos.user.service.UserQueryService;
import com.example.moikiitos.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    UserService userService;
    @Mock
    UserQueryService userQueryService;
    @Mock
    AuthService authService;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        UserController controller = new UserController(userService, userQueryService, authService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void follow_loginStatus_success() throws Exception {
        String loginUser = "User1";
        String user2 = "User2";
        when(authService.currentUserName()).thenReturn(loginUser);

        doNothing().when(userService).follow(eq(loginUser), eq(user2));

        this.mockMvc.perform(post("/users/" + user2 + "/follow"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void unfollow_loginStatus_success() throws Exception {
        String loginUser = "User1";
        String user2 = "User2";
        when(authService.currentUserName()).thenReturn(loginUser);

        doNothing().when(userService).unfollow(eq(loginUser), eq(user2));

        this.mockMvc.perform(post("/users/" + user2 + "/unfollow"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void findUser_userExists_returnUser() throws Exception {
        User user = new User();
        user.setName("name1");
        user.setEmail("name1@example.com");

        when(userQueryService.findUserByNameOrEmail(any(UserQueryDto.class))).thenReturn(user);

        this.mockMvc.perform(get("/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("{'name':'name1','email':'name1@example.com'}"));
    }

    @Test
    void findUser_userNotExists_returnNull() throws Exception {
        when(userQueryService.findUserByNameOrEmail(any(UserQueryDto.class))).thenReturn(null);

        this.mockMvc.perform(get("/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(""));
    }

    @Test
    void listFollowers_hasFollower_returnUsers() throws Exception {
        User user = new User();
        user.setName("name1");
        user.setEmail("name1@example.com");
        List<User> userList = List.of(user);
        var argCaptor = ArgumentCaptor.forClass(UserFollowQueryDto.class);
        when(userQueryService.listFollowers(argCaptor.capture())).thenReturn(userList);

        this.mockMvc.perform(get("/users/user1/followers"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("[{'name':'name1','email':'name1@example.com'}]"));
        UserFollowQueryDto queryDto = argCaptor.getValue();
        assertEquals("user1", queryDto.getName());
    }

    @Test
    void listFollowing_noFollowing_returnEmpty() throws Exception {
        List<User> userList = List.of();
        var argCaptor = ArgumentCaptor.forClass(UserFollowQueryDto.class);
        when(userQueryService.listFollowing(argCaptor.capture())).thenReturn(userList);

        this.mockMvc.perform(get("/users/user1/following"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("[]"));
        UserFollowQueryDto queryDto = argCaptor.getValue();
        assertEquals("user1", queryDto.getName());
    }
}