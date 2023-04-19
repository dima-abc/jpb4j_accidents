package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Accident;

import java.util.Optional;
import java.util.Set;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 2. IndexController. Таблица и вид. [#2092]
 * AccidentService интерфейс описывающий поведение
 * слоя бизнес логики модели Accident.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 16.04.2023
 */
public interface AccidentService {
    Accident save(Accident accident, Set<Integer> rIds);

    Optional<Accident> findById(int accidentId);

    boolean update(Accident accident, Set<Integer> rIds);

    boolean delete(int accidentId);

    Iterable<Accident> findAll();
}
