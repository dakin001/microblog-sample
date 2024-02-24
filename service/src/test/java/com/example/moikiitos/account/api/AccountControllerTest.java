package com.example.moikiitos.account.api;

import com.example.moikiitos.account.model.AccountExistsException;
import com.example.moikiitos.account.model.AccountLoginDto;
import com.example.moikiitos.account.model.AccountRegistrationDto;
import com.example.moikiitos.account.service.AccountService;
import com.example.moikiitos.shared.util.LoginContextUtils;
import com.example.moikiitos.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {
    @Mock
    AccountService accountService;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        AccountController controller = new AccountController(accountService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void login_rightPassword_success() throws Exception {
        try (var mockedStatic =
                     mockStatic(LoginContextUtils.class)) {
            String json = """ 
                    {
                      "nameOrEmail": "Sean",
                      "password": "123456"
                    }
                    """;
            User loginUser = new User();

            when(accountService.login(any(AccountLoginDto.class))).thenReturn(loginUser);

            this.mockMvc.perform(post("/account/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isNoContent());
            mockedStatic.verify(
                    () -> LoginContextUtils.setLoginUser(eq(loginUser)),
                    times(1)
            );
        }
    }

    @Test
    void login_wrongPassword_fail() throws Exception {
        String json = """ 
                {
                  "nameOrEmail": "Sean",
                  "password": "000000"
                }
                """;

        when(accountService.login(any(AccountLoginDto.class))).thenReturn(null);

        this.mockMvc.perform(post("/account/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("account or password not correct"));
    }

    @Test
    void register_availableAccount_success() throws Exception {
        String json = """ 
                {
                  "name": "Sean",
                  "email": "dengdajin@gmail.com",
                  "password": "123456"
                }
                """;

        this.mockMvc.perform(post("/account/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    void register_missEmail_fail() throws Exception {
        String json = """ 
                {
                  "name": "Sean",
                  "email": "",
                  "password": "123456"
                }
                """;

        this.mockMvc.perform(post("/account/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void register_unavailableAccount_fail() throws Exception {
        String json = """ 
                {
                  "name": "Sean",
                  "email": "dengdajin@gmail.com",
                  "password": "123456"
                }
                """;

        doThrow(new AccountExistsException()).when(accountService).register(any(AccountRegistrationDto.class));

        this.mockMvc.perform(post("/account/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isConflict())
                .andExpect(content().string("name or email already exists"));
    }

}