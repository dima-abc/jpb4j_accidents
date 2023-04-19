package ru.job4j.accidents.repository.memory;

import org.junit.jupiter.api.Test;
import ru.job4j.accidents.model.AccidentType;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 4. Form с композиционным объектом [#305522]
 * AccidentTypeMemRepository Test
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 17.04.2023
 */
class AccidentTypeMemRepositoryTest {
    private final AccidentTypeMemRepository types = new AccidentTypeMemRepository();

    @Test
    public void whenGetAllTypeThenReturnAllCollection() {
        var accidentType1 = new AccidentType(1, "Две машины");
        var accidentType2 = new AccidentType(2, "Машина и человек");
        var accidentType3 = new AccidentType(3, "Машина и велосипед");
        var accidentType4 = new AccidentType(4, "Слон и моська");

        var expectedAllType = Set.of(accidentType1, accidentType2, accidentType3, accidentType4);
        var actualAllType = types.findAll();

        assertThat(actualAllType).usingRecursiveComparison().isEqualTo(expectedAllType);
    }

    @Test
    public void whenFindByIdTypeThenReturnOptional() {
        var expectedType = Optional.of(new AccidentType(1, "Две машины"));
        var actualType = types.findById(expectedType.get().getId());

        assertThat(actualType).usingRecursiveComparison().isEqualTo(expectedType);
    }
}