package com.example.moikiitos.post.model;

import com.example.moikiitos.shared.PageQuery;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FeedQueryDto extends PageQuery {
    private Long userId;
}
