package ru.job4j.accidents.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 20.04.2023
 */
@Configuration
public class PasswordEncoderConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
