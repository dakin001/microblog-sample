package com.example.moikiitos.user.model;

import com.example.moikiitos.shared.PageQuery;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserFollowQueryDto extends PageQuery {
    private String name;
}
