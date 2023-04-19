package ru.job4j.accidents.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 2. IndexController. Таблица и вид. [#2092]
 * 1. Spring ORM [#2093]
 * Accident DAO модель описывающая автоинцидент.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 16.04.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "ACCIDENTS")
public class Accident {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "AC_NAME")
    private String name;
    @Column(name = "AC_TEXT")
    private String text;
    @Column(name = "AC_ADDRESS")
    private String address;
    @ManyToOne
    @JoinColumn(name = "TYPE_ID", foreignKey = @ForeignKey(name = "FK_ACCIDENT_TYPE"))
    private AccidentType type;
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "ACCIDENTS_RULES",
            uniqueConstraints = {@UniqueConstraint(
                    name = "accident_id_rule_id",
                    columnNames = {"ACCIDENT_ID", "RULE_ID"})},
            joinColumns = {@JoinColumn(name = "ACCIDENT_ID", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "RULE_ID", nullable = false)}
    )
    private Set<Rule> rules = new HashSet<>();

    public void addRule(Rule rule) {
        rules.add(rule);
    }
}
