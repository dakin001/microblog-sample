package com.example.moikiitos.post.api;

import com.example.moikiitos.post.model.FeedQueryDto;
import com.example.moikiitos.post.model.Post;
import com.example.moikiitos.post.service.FeedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
@Validated
public class FeedController {
    private final FeedService feedService;

    @Operation(summary = "user's feed", description = "Gets the feed of posts from a specific user", tags = {"post"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "search results matching criteria")
    })
    @GetMapping("/{name}")
    public List<Post> queryUserFeed(@PathVariable("name") @Size(max = 100) String name, @Valid FeedQueryDto reqDto) {
        reqDto.setName(name);
        return feedService.queryUserFeed(reqDto);
    }
}
