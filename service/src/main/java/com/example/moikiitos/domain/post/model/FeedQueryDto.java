package com.example.moikiitos.domain.post.model;

import com.example.moikiitos.domain.shared.PageQuery;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FeedQueryDto extends PageQuery {
    private Long userId;
}
