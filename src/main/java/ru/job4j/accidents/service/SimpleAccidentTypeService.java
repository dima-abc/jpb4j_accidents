package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeRepository;

import java.util.Collection;
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
    private final AccidentTypeRepository types;

    @Override
    public Optional<AccidentType> findByIdType(int typeId) {
        return types.findByIdType(typeId);
    }

    @Override
    public Collection<AccidentType> findAllType() {
        return types.findAll();
    }
}
