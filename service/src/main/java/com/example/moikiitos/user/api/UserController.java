package com.example.moikiitos.user.api;

import com.example.moikiitos.account.service.AuthService;
import com.example.moikiitos.user.model.User;
import com.example.moikiitos.user.model.UserFollowQueryDto;
import com.example.moikiitos.user.model.UserQueryDto;
import com.example.moikiitos.user.service.UserQueryService;
import com.example.moikiitos.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserQueryService userQueryService;
    private final AuthService authService;

    @Operation(summary = "follow user", description = "follow user api", tags = {"user"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "successful")})
    @PostMapping("{name}/follow")
    public ResponseEntity<Void> follow(@PathVariable("name") String name) {
        String user = authService.currentUserName();
        if (user != null) {
            userService.follow(user, name);
        }

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "unfollow user", description = "unfollow user api", tags = {"user"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "successful")})
    @PostMapping("{name}/unfollow")
    public ResponseEntity<Void> unfollow(@PathVariable("name") String name) {
        String user = authService.currentUserName();
        if (user != null) {
            userService.unfollow(user, name);
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
    @GetMapping("{name}/followers")
    public List<User> listFollowers(@PathVariable("name") String name, UserFollowQueryDto queryDto) {
        queryDto.setName(name);

        return userQueryService.listFollowers(queryDto);
    }

    @Operation(summary = "list Following", tags = {"user"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful")})
    @GetMapping("{name}/following")
    public List<User> listFollowing(@PathVariable("name") String name, UserFollowQueryDto queryDto) {
        queryDto.setName(name);

        return userQueryService.listFollowing(queryDto);
    }
}
