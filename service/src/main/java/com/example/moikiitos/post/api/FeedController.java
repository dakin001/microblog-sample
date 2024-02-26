package com.example.moikiitos.post.api;

import com.example.moikiitos.post.model.FeedQueryDto;
import com.example.moikiitos.post.model.Post;
import com.example.moikiitos.post.service.FeedService;
import com.example.moikiitos.shared.util.LoginContextUtils;
import com.example.moikiitos.user.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    @GetMapping()
    public List<Post> myFeed(@Valid FeedQueryDto reqDto) {
        User user = LoginContextUtils.getCurrentUser();
        if (user == null) {
            return new ArrayList<>();
        }

        reqDto.setUserId(user.getId());
        return feedService.queryUserFeed(reqDto);
    }

    @Operation(summary = "user's feed", description = "Gets the feed of posts from a specific user", tags = {"post"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "search results matching criteria")
    })
    @GetMapping("/{userId}")
    public List<Post> queryUserFeed(@PathVariable("userId") Long userId, @Valid FeedQueryDto reqDto) {
        reqDto.setUserId(userId);
        return feedService.queryUserFeed(reqDto);
    }
}
