--ACCIDENTS создание таблицы для хранения автонарушений.
CREATE TABLE accidents
(
    id SERIAL PRIMARY KEY,
    ac_name VARCHAR,
    ac_text TEXT,
    ac_address VARCHAR,
    type_id INT REFERENCES accident_types(id)
);