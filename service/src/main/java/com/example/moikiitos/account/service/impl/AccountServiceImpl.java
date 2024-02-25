package com.example.moikiitos.account.service.impl;

import com.example.moikiitos.account.model.Account;
import com.example.moikiitos.account.model.AccountExistsException;
import com.example.moikiitos.account.model.AccountLoginDto;
import com.example.moikiitos.account.model.AccountRegistrationDto;
import com.example.moikiitos.account.repository.AccountRepository;
import com.example.moikiitos.account.service.AccountService;
import com.example.moikiitos.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;

    @Override
    public void register(AccountRegistrationDto registrationDto) {
        validate(registrationDto);

        Account account = constructAccount(registrationDto);

        repository.add(account);
    }

    @Override
    public User login(AccountLoginDto loginDto) {
        Account account = getAccountByNameOrEmail(loginDto.getNameOrEmail());
        if (account == null) {
            return null;
        }
        if (passwordNotMatch(loginDto, account)) {
            return null;
        }

        return getUser(account);
    }

    private User getUser(Account account) {
        User user = new User();
        user.setId(account.getId());
        user.setName(account.getName());
        user.setEmail(account.getEmail());

        return user;
    }

    private boolean passwordNotMatch(AccountLoginDto loginDto, Account account) {
        return !passwordMatch(loginDto.getPassword(), account.getPassword());
    }

    private void validate(AccountRegistrationDto registrationDto) {
        Account account = repository.findByName(registrationDto.getName());
        if (account != null) {
            throw new AccountExistsException();
        }
        account = repository.findByEmail(registrationDto.getEmail());
        if (account != null) {
            throw new AccountExistsException();
        }
    }

    private Account constructAccount(AccountRegistrationDto registrationDto) {
        Account account = new Account();
        account.setName(registrationDto.getName());
        account.setEmail(registrationDto.getEmail());
        account.setPassword(hashPassword(registrationDto.getPassword()));
        return account;
    }

    private Account getAccountByNameOrEmail(String nameOrEmail) {
        Account account = repository.findByName(nameOrEmail);
        if (account != null) {
            return account;
        }
        return repository.findByEmail(nameOrEmail);
    }

    private String hashPassword(char[] password) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        // {bcrypt}
        return passwordEncoder.encode(new String(password));
    }

    private boolean passwordMatch(char[] password, String encodedPassword) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        return passwordEncoder.matches(new String(password), encodedPassword);
    }
}
