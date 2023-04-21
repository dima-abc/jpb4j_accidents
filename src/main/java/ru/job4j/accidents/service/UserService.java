package ru.job4j.accidents.service;

import ru.job4j.accidents.model.User;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.4. Security
 * 2. Регистрация пользователя [#296069]
 * UserService интерфейс описывает поведение бизнес логика обработки модели User
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 20.04.2023
 */
public interface UserService {
    Optional<User> save(User user);
}
