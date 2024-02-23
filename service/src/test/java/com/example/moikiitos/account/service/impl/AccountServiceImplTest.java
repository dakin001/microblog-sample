package com.example.moikiitos.account.service.impl;

import com.example.moikiitos.account.model.Account;
import com.example.moikiitos.account.model.AccountExistsException;
import com.example.moikiitos.account.model.AccountLoginDto;
import com.example.moikiitos.account.model.AccountRegistrationDto;
import com.example.moikiitos.account.repository.AccountRepository;
import com.example.moikiitos.account.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    AccountRepository repository;
    @Mock
    AuthService authService;

    AccountServiceImpl service;

    @BeforeEach
    public void setUp() {
        service = new AccountServiceImpl(repository, authService);
    }

    @Test
    void register_availableAccount_success() {
        // CASE
        AccountRegistrationDto registrationDto = new AccountRegistrationDto();
        registrationDto.setName("Sean");
        registrationDto.setEmail("Sean@example.com");
        registrationDto.setPassword("123456");

        // WHEN
        service.register(registrationDto);

        // THEN
        var argCaptor = ArgumentCaptor.forClass(Account.class);
        verify(repository, Mockito.times(1)).add(argCaptor.capture());
        Account account = argCaptor.getValue();
        assertNotEquals(registrationDto.getPassword(), account.getPassword());
    }


    @Test
    void register_unavailableName_fail() {
        // CASE
        AccountRegistrationDto registrationDto = new AccountRegistrationDto();
        registrationDto.setName("Sean");
        registrationDto.setEmail("Sean@example.com");
        registrationDto.setPassword("123456");

        Account exitsAccount = new Account();
        when(repository.findByName(anyString())).thenReturn(exitsAccount);

        // WHEN
        AccountExistsException exception = assertThrows(AccountExistsException.class, () -> service.register(registrationDto));

        // THEN
        verify(repository, never()).add(any(Account.class));
    }

    @Test
    void register_unavailableEmail_fail() {
        // CASE
        AccountRegistrationDto registrationDto = new AccountRegistrationDto();
        registrationDto.setName("Sean");
        registrationDto.setEmail("Sean@example.com");
        registrationDto.setPassword("123456");

        Account exitsAccount = new Account();
        when(repository.findByEmail(anyString())).thenReturn(exitsAccount);

        // WHEN
        AccountExistsException exception = assertThrows(AccountExistsException.class, () -> service.register(registrationDto));

        // THEN
        verify(repository, never()).add(any(Account.class));
    }

    @Test
    void login_byEmail_success() {
        // CASE
        AccountLoginDto loginDto = new AccountLoginDto();
        loginDto.setNameOrEmail("Sean@example.com");
        loginDto.setPassword("123456");

        Account exitsAccount = new Account();
        exitsAccount.setName("Sean");
        exitsAccount.setEmail("Sean@example.com");
        exitsAccount.setPasswordSalt("123");
        exitsAccount.setPassword("{bcrypt}$2a$10$EOWIrExEqX4ScNZ8i60hE.G/5Oz0U8vaNIEOOw.g6uahA8nDgrxqG");
        when(repository.findByEmail(anyString())).thenReturn(exitsAccount);

        // WHEN
        boolean loginSuccess = service.login(loginDto);

        // THEN
        assertTrue(loginSuccess);
        verify(authService, times(1)).login(any(Account.class));
    }

    @Test
    void login_ByName_success() {
        // CASE
        AccountLoginDto loginDto = new AccountLoginDto();
        loginDto.setNameOrEmail("Sean");
        loginDto.setPassword("123456");

        Account exitsAccount = new Account();
        exitsAccount.setName("Sean");
        exitsAccount.setEmail("Sean@example.com");
        exitsAccount.setPasswordSalt("123");
        exitsAccount.setPassword("{bcrypt}$2a$10$EOWIrExEqX4ScNZ8i60hE.G/5Oz0U8vaNIEOOw.g6uahA8nDgrxqG");
        when(repository.findByName(anyString())).thenReturn(exitsAccount);

        // WHEN
        boolean loginSuccess = service.login(loginDto);

        // THEN
        assertTrue(loginSuccess);
        verify(authService, times(1)).login(any(Account.class));
    }

    @Test
    void login_wrongAccount_fail() {
        // CASE
        AccountLoginDto loginDto = new AccountLoginDto();
        loginDto.setNameOrEmail("Sean");
        loginDto.setPassword("111111");

        Account exitsAccount = null;
        when(repository.findByName(anyString())).thenReturn(exitsAccount);

        // WHEN
        boolean loginSuccess = service.login(loginDto);

        // THEN
        assertFalse(loginSuccess);
        verify(authService, never()).login(any(Account.class));
    }

    @Test
    void login_wrongPassword_fail() {
        // CASE
        AccountLoginDto loginDto = new AccountLoginDto();
        loginDto.setNameOrEmail("Sean");
        loginDto.setPassword("111111");

        Account exitsAccount = new Account();
        exitsAccount.setName("Sean");
        exitsAccount.setEmail("Sean@example.com");
        exitsAccount.setPasswordSalt("123");
        exitsAccount.setPassword("{bcrypt}$2a$10$EOWIrExEqX4ScNZ8i60hE.G/5Oz0U8vaNIEOOw.g6uahA8nDgrxqG");
        when(repository.findByName(anyString())).thenReturn(exitsAccount);

        // WHEN
        boolean loginSuccess = service.login(loginDto);

        // THEN
        assertFalse(loginSuccess);
        verify(authService, never()).login(any(Account.class));
    }

}