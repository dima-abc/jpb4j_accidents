package ru.job4j.accidents.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 4. Form с композиционным объектом [#305522]
 * AccidentType DAO модель для для хранения справочника инцидента.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 17.04.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AccidentType {
    @EqualsAndHashCode.Include
    private int id;
    private String name;
}
