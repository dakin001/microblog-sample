package com.example.moikiitos.user.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Follower {
    private Long id;
    private User follower;
    private User following;
}
