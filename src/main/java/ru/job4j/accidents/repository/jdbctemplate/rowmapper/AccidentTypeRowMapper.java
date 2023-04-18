package ru.job4j.accidents.repository.jdbctemplate.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import ru.job4j.accidents.model.AccidentType;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 3.4.3. Template, ORM
 * 0. Spring DataSource [#6878]
 * AccidentTypeRowMapper реализация RowMapper для модели AccidentType
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 18.04.2023
 */
public class AccidentTypeRowMapper implements RowMapper<AccidentType> {
    @Override
    public AccidentType mapRow(ResultSet rs, int rowNum) throws SQLException {
        AccidentType accidentType = new AccidentType();
        accidentType.setId(rs.getInt("id"));
        accidentType.setName(rs.getString("type_name"));
        return accidentType;
    }
}
