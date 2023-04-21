package ru.job4j.accidents.repository.springdata;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Rule;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.3. Template, ORM
 * 2. Spring Data [#296073]
 * RuleSpringDataRepository реализация хранилища модели Rule,
 * при помощи SpringDataJpa
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 19.04.2023
 */
public interface RuleSpringDataRepository extends CrudRepository<Rule, Integer> {
}
