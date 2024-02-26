package com.example.moikiitos.account.service.impl;

import com.example.moikiitos.domain.account.model.Account;
import com.example.moikiitos.domain.account.model.AccountExistsException;
import com.example.moikiitos.domain.account.model.AccountLoginDto;
import com.example.moikiitos.domain.account.model.AccountRegistrationDto;
import com.example.moikiitos.domain.account.repository.AccountRepository;
import com.example.moikiitos.domain.account.service.impl.AccountServiceImpl;
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

    AccountServiceImpl service;

    @BeforeEach
    public void setUp() {
        service = new AccountServiceImpl(repository);
    }

    @Test
    void register_availableAccount_success() {
        // CASE
        AccountRegistrationDto registrationDto = new AccountRegistrationDto();
        registrationDto.setName("Sean");
        registrationDto.setEmail("Sean@example.com");
        registrationDto.setPassword("123456".toCharArray());

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
        registrationDto.setPassword("123456".toCharArray());

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
        registrationDto.setPassword("123456".toCharArray());

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
        loginDto.setPassword("123456".toCharArray());

        Account exitsAccount = new Account();
        exitsAccount.setName("Sean");
        exitsAccount.setEmail("Sean@example.com");
        exitsAccount.setPassword("{bcrypt}$2a$10$oO739LQrUgK3HO9.Yd5IOOaGinCwnfvGzylgrm7qBj0d5EJ8Frnb2");
        when(repository.findByEmail(anyString())).thenReturn(exitsAccount);

        // WHEN
        boolean loginSuccess = service.login(loginDto) != null;

        // THEN
        assertTrue(loginSuccess);
    }

    @Test
    void login_ByName_success() {
        // CASE
        AccountLoginDto loginDto = new AccountLoginDto();
        loginDto.setNameOrEmail("Sean");
        loginDto.setPassword("123456".toCharArray());

        Account exitsAccount = new Account();
        exitsAccount.setName("Sean");
        exitsAccount.setEmail("Sean@example.com");
        exitsAccount.setPassword("{bcrypt}$2a$10$oO739LQrUgK3HO9.Yd5IOOaGinCwnfvGzylgrm7qBj0d5EJ8Frnb2");
        when(repository.findByName(anyString())).thenReturn(exitsAccount);

        // WHEN
        boolean loginSuccess = service.login(loginDto) != null;

        // THEN
        assertTrue(loginSuccess);
    }

    @Test
    void login_wrongAccount_fail() {
        // CASE
        AccountLoginDto loginDto = new AccountLoginDto();
        loginDto.setNameOrEmail("Sean");
        loginDto.setPassword("111111".toCharArray());

        Account exitsAccount = null;
        when(repository.findByName(anyString())).thenReturn(exitsAccount);

        // WHEN
        boolean loginSuccess = service.login(loginDto) != null;

        // THEN
        assertFalse(loginSuccess);
    }

    @Test
    void login_wrongPassword_fail() {
        // CASE
        AccountLoginDto loginDto = new AccountLoginDto();
        loginDto.setNameOrEmail("Sean");
        loginDto.setPassword("111111".toCharArray());

        Account exitsAccount = new Account();
        exitsAccount.setName("Sean");
        exitsAccount.setEmail("Sean@example.com");
        exitsAccount.setPassword("{bcrypt}$2a$10$oO739LQrUgK3HO9.Yd5IOOaGinCwnfvGzylgrm7qBj0d5EJ8Frnb2");
        when(repository.findByName(anyString())).thenReturn(exitsAccount);

        // WHEN
        boolean loginSuccess = service.login(loginDto) != null;

        // THEN
        assertFalse(loginSuccess);


    }
}