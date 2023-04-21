package ru.job4j.accidents;

import liquibase.integration.spring.SpringLiquibase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 1. Техническое задание - проект "Автонарушители" [#260365]
 * AccidentsApplication точка входа в приложение.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 16.04.2023
 */
@SpringBootApplication
@Slf4j
public class AccidentsApplication {
    private static final String URI = "http://localhost:8080";

    @Bean
    public SpringLiquibase liquibase(DataSource ds) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db/dbchangelog.xml");
        liquibase.setDataSource(ds);
        return liquibase;
    }

    public static void main(String[] args) {
        SpringApplication.run(AccidentsApplication.class, args);
        log.info("Go to URI: {}", URI);
    }

}
