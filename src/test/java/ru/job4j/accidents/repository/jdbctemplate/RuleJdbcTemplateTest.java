package ru.job4j.accidents.repository.jdbctemplate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.job4j.accidents.model.Rule;

import java.sql.PreparedStatement;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * RuleJdbcTemplate test
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 23.04.2023
 */
@JdbcTest
class RuleJdbcTemplateTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static RuleJdbcTemplate rules;

    @BeforeEach
    private void initRepository() {
        rules = new RuleJdbcTemplate(jdbcTemplate);
    }

    @BeforeEach
    public void clearDataBase() {
        jdbcTemplate.update("DELETE FROM ACCIDENTS");
        jdbcTemplate.update("DELETE FROM RULES");
    }

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(jdbcTemplate).isNotNull();
        assertThat(rules).isNotNull();
    }

    @Test
    void whenFindByIdThenReturnRuleOptional() {
        KeyHolder key = new GeneratedKeyHolder();
        var sql = "INSERT INTO rules(rule_name) values(?)";
        var rule1 = new Rule(0, "rule1");
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, rule1.getName());
            return ps;
        }, key);
        rule1.setId(key.getKey().intValue());
        var ruleById = rules.findById(rule1.getId());
        assertThat(ruleById).isEqualTo(Optional.of(rule1));
    }

    @Test
    void findAll() {
        var allRule = rules.findAll();
        assertThat(allRule.isEmpty()).isTrue();
    }
}