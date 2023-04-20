package ru.job4j.accidents.model;

import lombok.*;

import javax.persistence.*;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.4. Security
 * 2. Регистрация пользователя [#296069]
 * User DAO  модель пользователей приложения.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 20.04.2023
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, updatable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private boolean enabled;
    @ManyToOne
    @JoinColumn(name = "AUTHORITY_ID", foreignKey = @ForeignKey(name = "FK_AUTHORITY_ID"))
    private Authority authority;
}
