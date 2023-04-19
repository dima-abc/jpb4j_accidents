package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Rule;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * RuleService интерфейс описывает поведение бизнес логики обработки модели Rule.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 17.04.2023
 */
public interface RuleService {
    Optional<Rule> findByIdRule(int ruleId);

    Iterable<Rule> findAllRule();
}
