package ru.job4j.accidents.service;

import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * AccidentTypeService интерфейс описывает поведение бизнес логики управления моделью AccidentType
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 17.04.2023
 */
public interface AccidentTypeService {
    Optional<AccidentType> findByIdType(int typeId);

    Collection<AccidentType> findAllType();
}
