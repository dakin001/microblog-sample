package com.example.moikiitos.account.api;

import com.example.moikiitos.account.model.AccountExistsException;
import com.example.moikiitos.account.model.AccountLoginDto;
import com.example.moikiitos.account.model.AccountRegistrationDto;
import com.example.moikiitos.account.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @Operation(summary = "user login", description = "user login to the system", tags = {"account"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "login successful"),
            @ApiResponse(responseCode = "400", description = "account or password not correct")})

    @PostMapping("login")
    public ResponseEntity<Object> login(@Validated @RequestBody AccountLoginDto loginDto) {
        boolean loginSuccess = accountService.login(loginDto);
        if (!loginSuccess) {
            return ResponseEntity.badRequest().body("account or password not correct");
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "register a new user", description = "register a new user", tags = {"account"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "user account created"),

            @ApiResponse(responseCode = "409", description = "name or email already exists")})

    @PostMapping("registration")
    public ResponseEntity<Object> register(@Validated @RequestBody AccountRegistrationDto registrationDto) {
        try {
            accountService.register(registrationDto);
        } catch (AccountExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("name or email already exists");
        }

        return ResponseEntity.created(URI.create("/")).build();
    }
}
