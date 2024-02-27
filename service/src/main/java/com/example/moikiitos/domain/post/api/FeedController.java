package com.example.moikiitos.domain.post.api;

import com.example.moikiitos.domain.post.model.FeedQueryDto;
import com.example.moikiitos.domain.post.model.Post;
import com.example.moikiitos.domain.post.service.FeedService;
import com.example.moikiitos.domain.post.service.UserFeedNotifyService;
import com.example.moikiitos.domain.shared.util.LoginContextUtils;
import com.example.moikiitos.domain.user.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
@Validated
public class FeedController {
    private final FeedService feedService;
    private final UserFeedNotifyService userFeedNotifyService;

    @Operation(summary = "login user's real time feed stream", description = "Real time Gets the feed of posts from login user", tags = {"post"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "search results matching criteria")
    })
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseBodyEmitter myFeed(@Valid FeedQueryDto reqDto) throws IOException {
        User user = LoginContextUtils.getCurrentUser();
        if (user == null) {
            return null;
        }

        reqDto.setUserId(user.getId());
        var posts = feedService.queryUserFeed(reqDto);

        var response = userFeedNotifyService.subscribe(user.getId());
        response.send(posts);
        return response;
    }

    @Operation(summary = "specific user's feed", description = "Gets the feed of posts from a specific user", tags = {"post"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "search results matching criteria")
    })
    @GetMapping("/{userId}")
    public List<Post> queryUserFeed(@PathVariable("userId") Long userId, @Valid FeedQueryDto reqDto) {
        reqDto.setUserId(userId);
        return feedService.queryUserFeed(reqDto);
    }
}
