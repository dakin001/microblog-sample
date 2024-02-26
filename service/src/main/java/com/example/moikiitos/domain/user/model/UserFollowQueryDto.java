package com.example.moikiitos.domain.user.model;

import com.example.moikiitos.domain.shared.PageQuery;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserFollowQueryDto extends PageQuery {
    private Long userId;
}
