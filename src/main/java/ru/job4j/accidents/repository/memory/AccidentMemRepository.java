package ru.job4j.accidents.repository.memory;

import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 2. IndexController. Таблица и вид. [#2092]
 * AccidentMemRepository хранилища модели Accident в памяти.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 16.04.2023
 */
public class AccidentMemRepository implements AccidentRepository {
    private final AtomicInteger key = new AtomicInteger(0);
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AccidentTypeMemRepository types = new AccidentTypeMemRepository();

    public AccidentMemRepository() {
        var rule1 = new Rule(1, "Статья 1");
        var rule2 = new Rule(2, "Статья 2");
        var rule3 = new Rule(3, "Статья 3");
        var rule4 = new Rule(4, "Статья 4");
        save(new Accident(
                0, "Accident_1", "text_Accident_1", "Address accident 1",
                types.findByIdType(1).get(),
                Set.of(rule1)));
        save(new Accident(
                0, "Accident_2", "text_Accident_2", "Address accident 2",
                types.findByIdType(2).get(),
                Set.of(rule1, rule2, rule3, rule4)));
        save(new Accident(
                0, "Accident_3", "text_Accident_3", "Address accident 3",
                types.findByIdType(3).get(),
                Set.of(rule1, rule2)));
        save(new Accident(
                0, "Accident_4", "text_Accident_4", "Address accident 4",
                types.findByIdType(4).get(),
                Set.of(rule1, rule2, rule3)));
        save(new Accident(
                0, "Accident_5", "text_Accident_5", "Address accident 5",
                types.findByIdType(2).get(),
                Set.of(rule2, rule3, rule4)));
    }

    @Override
    public Accident save(Accident accident) {
        accident.setId(key.incrementAndGet());
        return accidents.putIfAbsent(accident.getId(), accident);
    }

    @Override
    public Optional<Accident> findById(int accidentId) {
        return Optional.ofNullable(accidents.get(accidentId));
    }

    @Override
    public boolean update(Accident accident) {
        return accident.equals(accidents.put(accident.getId(), accident));
    }

    @Override
    public boolean delete(int accidentId) {
        return accidents.remove(accidentId) != null;
    }

    @Override
    public Collection<Accident> findAll() {
        return accidents.values();
    }
}
