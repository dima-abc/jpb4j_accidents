package ru.job4j.accidents.repository.jdbctemplate.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import ru.job4j.accidents.model.Rule;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 3.4.3. Template, ORM
 * 0. Spring DataSource [#6878]
 * RuleRowMapper реализация RowMapper для модели Rule.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 18.04.2023
 */
public class RuleRowMapper implements RowMapper<Rule> {
    @Override
    public Rule mapRow(ResultSet rs, int rowNum) throws SQLException {
        Rule rule = new Rule();
        rule.setId(rs.getInt("id"));
        rule.setName(rs.getString("rule_name"));
        return rule;
    }
}
