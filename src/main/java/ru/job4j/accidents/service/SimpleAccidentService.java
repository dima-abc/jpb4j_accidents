package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentRepository;

import java.util.Collection;
import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 2. IndexController. Таблица и вид. [#2092]
 * SimpleAccidentService реализация слоя бизнес логики модели Accident.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 16.04.2023
 */
@Service
@AllArgsConstructor
public class SimpleAccidentService implements AccidentService {
    private final AccidentRepository accidentRepository;

    @Override
    public Accident create(Accident accident) {
        return accidentRepository.create(accident);
    }

    @Override
    public Optional<Accident> findById(int accidentId) {
        return accidentRepository.findById(accidentId);
    }

    @Override
    public boolean update(Accident accident) {
        return accidentRepository.update(accident);
    }

    @Override
    public boolean delete(int accidentId) {
        return accidentRepository.delete(accidentId);
    }

    @Override
    public Collection<Accident> findAll() {
        return accidentRepository.findAll();
    }
}
