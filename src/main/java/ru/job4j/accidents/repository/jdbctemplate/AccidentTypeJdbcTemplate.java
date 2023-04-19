package ru.job4j.accidents.repository.jdbctemplate;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeRepository;
import ru.job4j.accidents.repository.jdbctemplate.rowmapper.AccidentTypeRowMapper;

import java.util.Collection;
import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 3.4.3. Template, ORM
 * 0. Spring DataSource [#6878]
 * AccidentTypeJdbcTemplate реализация хранилища модели AccidentType,
 * с помощью Spring JdbcTemplate
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 18.04.2023
 */
@AllArgsConstructor
public class AccidentTypeJdbcTemplate implements AccidentTypeRepository {
    private final JdbcTemplate jdbc;

    @Override
    public Optional<AccidentType> findByIdType(int typeId) {
        AccidentType accidentType = jdbc.queryForObject(
                "SELECT * FROM accident_types WHERE id = ?",
                new AccidentTypeRowMapper(), typeId);
        return Optional.ofNullable(accidentType);
    }

    @Override
    public Collection<AccidentType> findAll() {
        return jdbc.query(
                "SELECT * FROM accident_types",
                new AccidentTypeRowMapper());
    }
}
