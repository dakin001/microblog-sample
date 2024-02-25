package com.example.moikiitos.account.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountRegistrationDto {
    @Size(max = 100)
    @NotEmpty
    private String name;
    @NotEmpty
    @Email
    @Size(max = 100)
    private String email;
    @NotEmpty
    @Size(max = 100)
    private char[] password;
}
