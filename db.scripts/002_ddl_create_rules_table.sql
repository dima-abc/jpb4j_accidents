--RULES создание таблица для хранения статей которые предусмотрены за нарушение.
CREATE TABLE rules
(
    id        SERIAL PRIMARY KEY,
    rule_name VARCHAR not null unique
);