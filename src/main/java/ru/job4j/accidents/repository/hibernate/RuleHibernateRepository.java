package ru.job4j.accidents.repository.hibernate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 3.4.3. Template, ORM
 * 1. Spring ORM [#2093]
 * RuleHibernateRepository реализация хранилища модели Rule
 * с использованием Spring-ORM Hibernate
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 19.04.2023
 */
@Repository
@AllArgsConstructor
public class RuleHibernateRepository implements RuleRepository {
    private final CrudRepository crud;

    @Override
    public Optional<Rule> findByIdRule(int ruleId) {
        return crud.optional(
                "from Rule where id =:ruleId",
                Rule.class,
                Map.of("ruleId", ruleId));
    }

    @Override
    public Collection<Rule> findAllRule() {
        return crud.query("from Rule", Rule.class);
    }
}
