package ru.job4j.accidents.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 3.4.3. Template, ORM
 * 0. Spring DataSource [#6878]
 * JdbcConfiguration создание подключения и пула подключений к базе данных
 * при помощи JdbcTemplate и DataSource
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 18.04.2023
 */
@Configuration
@EnableTransactionManagement
public class JdbcConfiguration {

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
