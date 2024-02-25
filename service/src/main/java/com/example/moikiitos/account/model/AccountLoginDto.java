package com.example.moikiitos.account.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountLoginDto {
    @Size(max = 100)
    @NotEmpty
    private String nameOrEmail;
    @Size(max = 100)
    @NotEmpty
    private char[] password;
}
