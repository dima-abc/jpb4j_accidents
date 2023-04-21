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
 * 5. Form с агрегационными объектами [#305523]
 * 1. Spring ORM [#2093]
 * Rule DAO модель описывающая статью нарушения.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 17.04.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "RULES")
public class Rule {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "rule_name", nullable = false, unique = true, updatable = false)
    private String name;
}
