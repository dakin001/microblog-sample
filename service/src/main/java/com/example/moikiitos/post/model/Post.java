package com.example.moikiitos.post.model;

import com.example.moikiitos.user.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Post {
    private Long id;
    private User user;
    private String content;
    private LocalDateTime createAt;
}
