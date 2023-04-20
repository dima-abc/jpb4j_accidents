package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.springdata.AccidentSpringDataRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
@Slf4j
public class SimpleAccidentService implements AccidentService {
    private final AccidentSpringDataRepository accidentRepository;

    @Override
    public Accident save(Accident accident, Set<Integer> rIds) {
        var rules = getRuleByRIDs(rIds);
        accident.setRules(rules);
        return accidentRepository.save(accident);
    }

    @Override
    public Optional<Accident> findById(int accidentId) {
        return accidentRepository.findById(accidentId);
    }

    @Override
    public boolean update(Accident accident, Set<Integer> rIds) {
        return accident.equals(save(accident, rIds));
    }

    @Override
    public boolean delete(int accidentId) {
        try {
            accidentRepository.deleteById(accidentId);
            return true;
        } catch (Exception e) {
            log.error("Error delete Accident ID: {}", accidentId);
            return false;
        }
    }

    @Override
    public Iterable<Accident> findAll() {
        return accidentRepository.findAll();
    }

    /**
     * Преобразовывает Set<Integer> в Set<Rule>
     *
     * @param rIds Set<Integer>
     * @return Set<Rule>
     */
    private Set<Rule> getRuleByRIDs(Set<Integer> rIds) {
        Set<Rule> result = new HashSet<>();
        if (rIds != null) {
            result = rIds.stream().map(id -> new Rule(id, String.valueOf(id)))
                    .collect(Collectors.toSet());
        }
        return result;
    }
}
