package ru.job4j.accidents.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.4. Security
 * 0. Spring Security [#6879]
 * 1. Авторизация через JDBC [#2094]
 * SecurityConfig отдельный класс в котором сделаны настройки для авторизации.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 19.04.2023
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final DataSource dataSource;

    @Bean
    public UserDetailsManager authenticateUsers() {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.setUsersByUsernameQuery("SELECT username, password, enabled "
                                      + "FROM users WHERE username = ?");
        users.setAuthoritiesByUsernameQuery("SELECT u.username, a.authority "
                                            + "FROM authorities AS a, users AS u "
                                            + "WHERE u.username = ? and u.authority_id = a.id");
        return users;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/reg")
                .permitAll()
                .antMatchers("/**")
                .hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .csrf()
                .disable();
        return http.build();
    }

    /**
     * Пропускаем (не блокируем) картинки и логотипы оформления стартовых страниц.
     *
     * @return WebSecurityCustomizer
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/css/**", "/js/**", "/images/logo/**");
    }
}
