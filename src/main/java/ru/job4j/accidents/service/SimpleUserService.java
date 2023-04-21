package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.springdata.UserSpringDataRepository;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.4. Security
 * 2. Регистрация пользователя [#296069]
 * SimpleUserService бизнес логика обработки модели User
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 20.04.2023
 */
@Service
@AllArgsConstructor
@Slf4j
public class SimpleUserService implements UserService {
    private final UserSpringDataRepository users;

    @Override
    public Optional<User> save(User user) {
        try {
            users.save(user);
            return Optional.of(user);
        } catch (Exception e) {
            log.error("Save user errors User: {}, Exception: {}", user, e);
            return Optional.empty();
        }
    }
}
