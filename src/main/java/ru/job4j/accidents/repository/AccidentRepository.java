package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 2. IndexController. Таблица и вид. [#2092]
 * AccidentRepository интерфейс описывающий поведение хранилища модели Accident.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 16.04.2023
 */
public interface AccidentRepository {
    Accident save(Accident accident);

    Optional<Accident> findById(int accidentId);

    boolean update(Accident accident);

    boolean delete(int accidentId);

    Collection<Accident> findAll();
}
