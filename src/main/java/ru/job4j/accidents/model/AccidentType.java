package ru.job4j.accidents.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 4. Form с композиционным объектом [#305522]
 * 1. Spring ORM [#2093]
 * AccidentType DAO модель для для хранения справочника инцидента.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 17.04.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "ACCIDENT_TYPES")
public class AccidentType {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "type_name", nullable = false, unique = true)
    private String name;
}
