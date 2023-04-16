package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Optional;

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
    Accident create(Accident accident);

    boolean update(Accident accident);

    Optional<Accident> findById(int accidentId);

    Collection<Accident> findAll();
}
