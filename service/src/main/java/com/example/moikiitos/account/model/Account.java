package com.example.moikiitos.account.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Account {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String passwordSalt;
}
