package com.example.moikiitos.post.api;

import com.example.moikiitos.post.model.PostCreateDto;
import com.example.moikiitos.post.service.PostService;
import com.example.moikiitos.shared.util.LoginContextUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @Operation(summary = "submit a new post", description = "submit a new post", tags = {"post"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "post created"),

            @ApiResponse(responseCode = "400", description = "invalid input, object invalid")})
    @PostMapping
    public ResponseEntity<Void> createPost(@Validated @RequestBody PostCreateDto reqDto) {
        postService.createPost(LoginContextUtils.currentUser(), reqDto);

        return ResponseEntity.created(URI.create("/")).build();
    }
}
