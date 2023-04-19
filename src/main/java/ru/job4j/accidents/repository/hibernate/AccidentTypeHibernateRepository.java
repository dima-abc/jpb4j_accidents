package ru.job4j.accidents.repository.hibernate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.3. Template, ORM
 * 1. Spring ORM [#2093]
 * AccidentTypeHibernateRepository реализация хранилища модели AccidentType
 * с использованием Spring-ORM Hibernate
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 19.04.2023
 */
@Repository
@AllArgsConstructor
public class AccidentTypeHibernateRepository implements AccidentTypeRepository {
    private final CrudRepository crud;

    @Override
    public Optional<AccidentType> findByIdType(int typeId) {
        return crud.optional(
                "from AccidentType where id =:typeId",
                AccidentType.class,
                Map.of("typeId", typeId)
        );
    }

    @Override
    public Collection<AccidentType> findAll() {
        return crud.query("from AccidentType", AccidentType.class);
    }
}
