package com.example.moikiitos.domain.user.api;

import com.example.moikiitos.domain.shared.PageResult;
import com.example.moikiitos.domain.shared.util.LoginContextUtils;
import com.example.moikiitos.domain.user.model.User;
import com.example.moikiitos.domain.user.model.UserFollowQueryDto;
import com.example.moikiitos.domain.user.model.UserQueryDto;
import com.example.moikiitos.domain.user.service.UserQueryService;
import com.example.moikiitos.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;
    private final UserQueryService userQueryService;

    @Operation(summary = "follow user", description = "follow user api", tags = {"user"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "successful")})
    @PostMapping("{userId}/follow")
    public ResponseEntity<Void> follow(@PathVariable("userId") Long userId) {
        User user = LoginContextUtils.getCurrentUser();
        if (user != null) {
            userService.follow(user, userId);
        }

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "unfollow user", description = "unfollow user api", tags = {"user"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "successful")})
    @PostMapping("{userId}/unfollow")
    public ResponseEntity<Void> unfollow(@PathVariable("userId") Long userId) {
        User user = LoginContextUtils.getCurrentUser();
        if (user != null) {
            userService.unfollow(user, userId);
        }

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "find user", tags = {"user"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful")})
    @GetMapping
    public User findUser(UserQueryDto queryDto) {
        return userQueryService.findUserByNameOrEmail(queryDto);
    }

    @Operation(summary = "list Followers", tags = {"user"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful")})
    @GetMapping("{userId}/followers")
    public PageResult<User> listFollowers(@PathVariable("userId") Long userId, UserFollowQueryDto queryDto) {
        queryDto.setUserId(userId);

        return userQueryService.listFollowers(queryDto);
    }

    @Operation(summary = "list Following", tags = {"user"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful")})
    @GetMapping("{userId}/following")
    public PageResult<User> listFollowing(@PathVariable("userId") Long userId, UserFollowQueryDto queryDto) {
        queryDto.setUserId(userId);

        return userQueryService.listFollowing(queryDto);
    }
}
