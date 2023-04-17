package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
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
@Repository
public class AccidentMemRepository implements AccidentRepository {
    private final AtomicInteger key = new AtomicInteger(0);
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public AccidentMemRepository() {
        create(new Accident(0, "Accident_1", "text_Accident_1", "Address accident 1"));
        create(new Accident(0, "Accident_2", "text_Accident_2", "Address accident 2"));
        create(new Accident(0, "Accident_3", "text_Accident_3", "Address accident 3"));
        create(new Accident(0, "Accident_4", "text_Accident_4", "Address accident 4"));
        create(new Accident(0, "Accident_5", "text_Accident_5", "Address accident 5"));
    }

    @Override
    public Accident create(Accident accident) {
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
