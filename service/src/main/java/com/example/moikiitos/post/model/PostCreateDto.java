package com.example.moikiitos.post.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostCreateDto {
    @Size(max = 2000)
    @NotEmpty
    private String content;
}
