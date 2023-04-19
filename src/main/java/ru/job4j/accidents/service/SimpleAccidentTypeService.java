package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.springdata.AccidentTypeSpringDataRepository;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * SimpleAccidentTypeService слой бизнес логики управления моделью AccidentType
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 17.04.2023
 */
@Service
@AllArgsConstructor
public class SimpleAccidentTypeService implements AccidentTypeService {
    private final AccidentTypeSpringDataRepository types;

    @Override
    public Optional<AccidentType> findByIdType(int typeId) {
        return types.findById(typeId);
    }

    @Override
    public Iterable<AccidentType> findAllType() {
        return types.findAll();
    }
}
