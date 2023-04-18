package ru.job4j.accidents.repository.memory;

import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 5. Form с агрегационными объектами [#305523]
 * RuleMemRepository хранилище модели Rule в памяти.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 17.04.2023
 */
public class RuleMemRepository implements RuleRepository {
    private final AtomicInteger key = new AtomicInteger(0);
    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();

    public RuleMemRepository() {
        rules.put(key.incrementAndGet(), new Rule(key.get(), "Статья 1"));
        rules.put(key.incrementAndGet(), new Rule(key.get(), "Статья 2"));
        rules.put(key.incrementAndGet(), new Rule(key.get(), "Статья 3"));
        rules.put(key.incrementAndGet(), new Rule(key.get(), "Статья 4"));
    }

    @Override
    public Optional<Rule> findByIdRule(int ruleId) {
        return Optional.ofNullable(rules.get(ruleId));
    }

    @Override
    public Collection<Rule> findAllRule() {
        return rules.values();
    }
}
