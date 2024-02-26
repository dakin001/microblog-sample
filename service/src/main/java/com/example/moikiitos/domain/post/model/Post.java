package com.example.moikiitos.domain.post.model;

import com.example.moikiitos.domain.user.model.User;
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
