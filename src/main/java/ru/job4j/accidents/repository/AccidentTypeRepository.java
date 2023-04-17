package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 4. Form с композиционным объектом [#305522]
 * AccidentTypeRepository интерфейс описывающий поведение хранилища типов нарушений
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 17.04.2023
 */
public interface AccidentTypeRepository {
    Optional<AccidentType> findByIdType(int typeId);

    Collection<AccidentType> findAll();
}
