package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.AccidentsApplication;
import ru.job4j.accidents.model.Authority;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.springdata.AuthoritySpringDataRepository;
import ru.job4j.accidents.service.UserService;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.web.servlet.function.RequestPredicates.param;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.5. Tests
 * 0. Spring test [#6881]
 * UserController TEST.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 21.04.2023
 */
@SpringBootTest(classes = AccidentsApplication.class)
@AutoConfigureMockMvc
class UserControllerTest {
    @MockBean
    private UserService users;
    @MockBean
    private AuthoritySpringDataRepository authorities;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenGetLoginPageThenReturnLoginPageStatusOk() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users/login"));
    }

    @Test
    void whenGetLoginPageErrorParamNotNullThenReturnLoginPageMessageError() throws Exception {
        var errorMessage = "Username or Password is incorrect !!";
        this.mockMvc.perform(get("/login?error=true"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("errorMessage", errorMessage))
                .andExpect(view().name("users/login"));
    }

    @Test
    void whenGetLoginPageLogoutParamNotNullThenReturnLoginPageMessageError() throws Exception {
        var errorMessage = "You have been successfully logged out !!";
        this.mockMvc.perform(get("/login?logout=true"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("errorMessage", errorMessage))
                .andExpect(view().name("users/login"));
    }

    @Test
    void whenGetRegistrationUserPageThenReturnUserPageStatusOk() throws Exception {
        this.mockMvc.perform(get("/reg"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users/registration"));
    }

    @Test
    void wenGetRegistrationUserErrorParamNotNullThenReturnRegistrationPageAndErrorMessage() throws Exception {
        var errorMessage = "Username is already taken";
        this.mockMvc.perform(get("/reg?error=true"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("errorMessage", errorMessage))
                .andExpect(view().name("users/registration"));
    }


    @Test
    void thenPostRegSaveUserThenReturnRegPageErrorAndArgumentCaptureEquals() throws Exception {
        var authority = new Authority(1, "ROLE_USER");
        var user = new User(0, "login", "password", true, authority);
        when(authorities.findByAuthority(authority.getAuthority())).thenReturn(authority);

        this.mockMvc.perform(post("/reg")
                        .param("username", user.getUsername())
                        .param("password", user.getPassword()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/reg?error=true"));

        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        verify(users).save(argument.capture());

        assertThat(argument.getValue().getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    void whenPostRegSaveUserThenReturnLoginPage() throws Exception {
        var authority = new Authority(1, "ROLE_USER");
        var user = new User(0, "login", "password", true, authority);
        when(authorities.findByAuthority(authority.getAuthority())).thenReturn(authority);
        when(users.save(user)).thenReturn(Optional.of(user));

        this.mockMvc.perform(post("/reg")
                        .param("username", user.getUsername())
                        .param("password", user.getPassword()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    void whenLogoutPageThenReturnRedirectLoginLogoutTrue() throws Exception {
        mockMvc.perform(get("/logout"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?logout=true"));
    }
}