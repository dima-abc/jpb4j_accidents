--Наполнение таблицы ролями пользователей
insert into authorities (authority)
values ('ROLE_USER');
insert into authorities (authority)
values ('ROLE_ADMIN');

--Наполнение таблицы пользователей
--Пароль root в PasswordEncoder для пользователя admin
-- $2a$10$6RxSViOYxrVx9EVSHgpLSu21IAC51sTsuCZhEsVFAHIjcjqRGkFDO
--Пароль 123 в PasswordEncoder
-- $2a$10$csxqM5AcrCR6PZHLtkiBpO35vm1aIpd2K/PErUfs2UpwVphpsBkya

--пользователь admin
insert into users(username, enabled, password, authority_id)
values ('admin', true, '$2a$10$6RxSViOYxrVx9EVSHgpLSu21IAC51sTsuCZhEsVFAHIjcjqRGkFDO',
        (select id from authorities where authority = 'ROLE_ADMIN' limit 1));
--пользователь user
insert into users(username, enabled, password, authority_id)
values ('user', true, '$2a$10$csxqM5AcrCR6PZHLtkiBpO35vm1aIpd2K/PErUfs2UpwVphpsBkya',
        (select id from authorities where authority = 'ROLE_USER' limit 1));