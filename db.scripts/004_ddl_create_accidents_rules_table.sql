--ACCIDENTS_RULES создание таблицы для хранение связи MANY to MANY
-- между таблицами ACCIDENTS и RULES
CREATE TABLE accidents_rules
(
    id          SERIAL PRIMARY KEY,
    accident_id INT NOT NULL REFERENCES accidents (id),
    rule_id     INT NOT NULL REFERENCES rules (id),
    UNIQUE (accident_id, rule_id)
);