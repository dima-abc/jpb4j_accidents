package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 5. Form с агрегационными объектами [#305523]
 * RuleRepository интерфейс описывает поведение хранилища модели Rule
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 17.04.2023
 */
public interface RuleRepository {
    Optional<Rule> findByIdRule(int ruleId);

    Collection<Rule> findAllRule();
}
