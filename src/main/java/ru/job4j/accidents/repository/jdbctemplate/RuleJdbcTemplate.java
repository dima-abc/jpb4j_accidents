package ru.job4j.accidents.repository.jdbctemplate;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleRepository;
import ru.job4j.accidents.repository.jdbctemplate.rowmapper.RuleRowMapper;

import java.util.Collection;
import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 3.4.3. Template, ORM
 * 0. Spring DataSource [#6878]
 * RuleJdbcTemplate реализация хранилища модели Rule,
 * с помощью Spring JdbcTemplate
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 18.04.2023
 */
@AllArgsConstructor
public class RuleJdbcTemplate implements RuleRepository {
    private final JdbcTemplate jdbc;

    @Override
    public Optional<Rule> findById(int ruleId) {
        Rule rule = jdbc.queryForObject(
                "SELECT * FROM rules AS ru WHERE ru.id = ?",
                new RuleRowMapper(), ruleId);
        return Optional.ofNullable(rule);
    }

    @Override
    public Collection<Rule> findAll() {
        return jdbc.query("SELECT * FROM rules",
                new RuleRowMapper());
    }
}
