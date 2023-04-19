package ru.job4j.accidents.repository.hibernate;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.QueryHints;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 3.4.3. Template, ORM
 * 1. Spring ORM [#2093]
 * CrudRepository Лямбды и шаблон Command для реализации методов Hibernate
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 19.04.2023
 */
@Component
@AllArgsConstructor
@Slf4j
public class CrudRepository {
    private final SessionFactory sessionFactory;

    public void run(Consumer<Session> command) {
        tx(session -> {
            command.accept(session);
            return null;
        });
    }

    public void run(String query, Map<String, Object> args) {
        Consumer<Session> command = session -> {
            var sessionQuery = session.createQuery(query);
            for (Map.Entry<String, Object> arg : args.entrySet()) {
                sessionQuery.setParameter(arg.getKey(), arg.getValue());
            }
            sessionQuery.executeUpdate();
        };
        run(command);
    }

    public <T> Optional<T> optional(String query, Class<T> cl, Map<String, Object> args) {
        Function<Session, Optional<T>> command = session -> {
            var sessionQuery = session.createQuery(query, cl);
            for (Map.Entry<String, Object> arg : args.entrySet()) {
                sessionQuery.setParameter(arg.getKey(), arg.getValue());
            }
            return sessionQuery.uniqueResultOptional();
        };
        return tx(command);
    }

    public <T> List<T> query(String query, Class<T> cl) {
        Function<Session, List<T>> command = session -> session
                .createQuery(query, cl)
                .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                .list();
        return tx(command);
    }

    public <T> List<T> query(String query, Class<T> cl, Map<String, Object> args) {
        Function<Session, List<T>> command = session -> {
            var sessionQuery = session
                    .createQuery(query, cl);
            for (Map.Entry<String, Object> arg : args.entrySet()) {
                sessionQuery.setParameter(arg.getKey(), arg.getValue());
            }
            return sessionQuery
                    .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                    .list();
        };
        return tx(command);
    }

    /**
     * Лямбда выражения
     *
     * @param command Function
     * @param <T>     type
     * @return type
     */
    public <T> T tx(Function<Session, T> command) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            T rsl = command.apply(session);
            transaction.commit();
            return rsl;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Exception TX: {}", e.getMessage());
            throw e;
        } finally {
            session.close();
        }
    }
}
