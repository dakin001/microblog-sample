package com.example.moikiitos.domain.account.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountLoginDto {
    @Schema(example = "user1")
    @Size(max = 100)
    @NotEmpty
    private String nameOrEmail;

    @Schema(example = "***")
    @Size(max = 100)
    @NotEmpty
    private char[] password;
}
