package com.example.moikiitos.domain.user.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Follower {
    private Long id;
    private User follower;
    private User following;
}
