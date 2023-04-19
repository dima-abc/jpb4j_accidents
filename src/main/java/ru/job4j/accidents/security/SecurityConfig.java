package ru.job4j.accidents.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.4. Security
 * 0. Spring Security [#6879]
 * SecurityConfig отдельный класс в котором сделаны настройки для авторизации.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 19.04.2023
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("user")
                .password(passwordEncoder.encode("123456")).roles("USER")
                .and()
                .withUser("admin")
                .password(passwordEncoder.encode("123456")).roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login")
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
    }
}
