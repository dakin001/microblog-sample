package com.example.moikiitos.domain.account.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "***")
    @NotEmpty
    @Size(max = 100)
    private char[] password;
}
