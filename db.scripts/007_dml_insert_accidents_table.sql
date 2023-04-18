--Наполнение таблицы ACCIDENTS справочными данными
INSERT INTO accidents(ac_name, ac_text, ac_address, type_id)
SELECT 'Авария на беговой', 'Проишествие без жертв', 'г.Краснодар ул.Беговая 4', tp.id
FROM accident_types AS tp
WHERE tp.type_name = 'Две машины' LIMIT 1;

INSERT INTO accidents(ac_name, ac_text, ac_address, type_id)
SELECT 'Сбили собаку', 'собака залезла под слана, в результате скончалась',
       'г.Тула ул.Лаптевых 33', tp.id
FROM accident_types AS tp
WHERE tp.type_name = 'Слон и моська' LIMIT 1;

INSERT INTO accidents(ac_name, ac_text, ac_address, type_id)
SELECT 'Наезд на велосипедиста', 'Велосипедист был под веществами возили на освидетельствование',
       'г.Москва ул.Емиля Кукурузы 159', tp.id
FROM accident_types AS tp
WHERE tp.type_name = 'Машина и велосипед' LIMIT 1;

INSERT INTO accidents(ac_name, ac_text, ac_address, type_id)
SELECT 'Не пропустили пешехода', 'Пешеход в больнице травмы 2 степени',
       'г.Томск ул.Скользкая 11', tp.id
FROM accident_types AS tp
WHERE tp.type_name = 'Машина и человек' LIMIT 1;