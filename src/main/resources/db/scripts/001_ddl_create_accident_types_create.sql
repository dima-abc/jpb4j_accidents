--ACCIDENT_TYPES создание таблицы для хранения видов нарушений.
CREATE TABLE accident_types
(
    id     SERIAL PRIMARY KEY,
    type_name VARCHAR not null unique
);
