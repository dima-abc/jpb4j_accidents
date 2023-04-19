package ru.job4j.accidents.repository.hibernate;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.3. Template, ORM
 * 1. Spring ORM [#2093]
 * AccidentHibernateRepository реализация хранилища модели Accident
 * * с использованием Spring-ORM Hibernate
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 19.04.2023
 */
@AllArgsConstructor
@Slf4j
public class AccidentHibernateRepository implements AccidentRepository {
    private final CrudRepository crud;

    @Override
    public Accident save(Accident accident) {
        crud.run(session -> session.persist(accident));
        return accident;
    }

    @Override
    public Optional<Accident> findById(int accidentId) {
        return crud.optional(
                "FROM Accident AS ac "
                + "LEFT JOIN FETCH ac.type AS ty "
                + "LEFT JOIN FETCH ac.rules AS ru "
                + "WHERE ac.id =:accidentId",
                Accident.class,
                Map.of("accidentId", accidentId));
    }

    @Override
    public boolean update(Accident accident) {
        try {
            crud.run(session -> session.update(accident));
            return true;
        } catch (Exception e) {
            log.error("Error update Accident: {}, exception: {}", accident, e);
            return false;
        }
    }

    @Override
    public boolean delete(int accidentId) {
        try {
            crud.run("delete from Accident where id =:accidentId",
                    Map.of("accidentId", accidentId));
            return true;
        } catch (Exception e) {
            log.error("Error delete Accident ID: {}, exception: {}", e);
            return false;
        }
    }

    @Override
    public Collection<Accident> findAll() {
        return crud.query(
                "SELECT DISTINCT ac FROM Accident AS ac "
                + "LEFT JOIN FETCH ac.type AS ty "
                + "LEFT JOIN FETCH ac.rules AS ru ORDER BY ac.id ASC",
                Accident.class);
    }
}
