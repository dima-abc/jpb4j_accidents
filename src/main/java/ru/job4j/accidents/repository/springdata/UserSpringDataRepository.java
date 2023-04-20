package ru.job4j.accidents.repository.springdata;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.User;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.4. Security
 * 2. Регистрация пользователя [#296069]
 * UserSpringDataRepository хранилище моделей User пользователей при помощи Spring-DATA-JPA
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 20.04.2023
 */
public interface UserSpringDataRepository extends CrudRepository<User, Integer> {
}
