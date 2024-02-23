package com.example.moikiitos.account.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountLoginDto {
    @NotEmpty
    private String nameOrEmail;
    @NotEmpty
    private String password;
}
