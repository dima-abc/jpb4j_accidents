package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleRepository;

import java.util.Collection;
import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 5. Form с агрегационными объектами [#305523]
 * SimpleRuleService реализация бизнес логики работы с моделью Rule
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 17.04.2023
 */
@Service
@AllArgsConstructor
public class SimpleRuleService implements RuleService {
    private final RuleRepository ruleRepository;

    @Override
    public Optional<Rule> findByIdRule(int ruleId) {
        return ruleRepository.findByIdRule(ruleId);
    }

    @Override
    public Collection<Rule> findAllRule() {
        return ruleRepository.findAllRule();
    }
}
