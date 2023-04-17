package ru.job4j.accidents.repository.memory;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 4. Form с композиционным объектом [#305522]
 * AccidentTypeMemRepository хранилище типов нарушений в памяти.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 17.04.2023
 */
@Repository
public class AccidentTypeMemRepository implements AccidentTypeRepository {
    private final Map<Integer, AccidentType> types = new ConcurrentHashMap<>();

    public AccidentTypeMemRepository() {
        AtomicInteger key = new AtomicInteger(0);
        types.put(key.incrementAndGet(), new AccidentType(key.get(), "Две машины"));
        types.put(key.incrementAndGet(), new AccidentType(key.get(), "Машина и человек"));
        types.put(key.incrementAndGet(), new AccidentType(key.get(), "Машина и велосипед"));
        types.put(key.incrementAndGet(), new AccidentType(key.get(), "Слон и моська"));
    }

    @Override
    public Optional<AccidentType> findByIdType(int typeId) {
        return Optional.ofNullable(types.get(typeId));
    }

    @Override
    public Collection<AccidentType> findAll() {
        return types.values();
    }
}
