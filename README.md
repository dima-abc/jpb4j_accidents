![logo_accidents.png](src%2Fmain%2Fresources%2Fstatic%2Fimages%2Flogo%2Flogo_accidents.png)

# Проект автонарушители

<h3>Описание проекта</h3>
<h6>
Приложение реализует учет и хранение автонарушений. <br>
В системе существуют две роли. Обычные пользователи и автоинспекторы. <br>
Пользователь добавляет описание автонарушение. <br>
В заявлении указывает: адрес, номер машины, описание нарушения и фотографию нарушения. <br>
У заявки есть статус. Принята. Отклонена. Завершена. Вид системы. <br>
Главная страница - это поиск с таблицей. <br>
Таблица содержит данные по нарушению, с описанием адресом и статьями нарушений. <br> 
Для входа в приложения необходима авторизация. <br>
Авторизованный пользователь может просматривать заявки, редактировать и добавлять новые. <br>
</h6>

<h3>Стек технологий </h3>
Java 17 <br>
PostgreSQL 14 <br>
Spring BOOT 2.7.4 <br>
Spring jdbc
Spring orm
Spring data jpa
Spring security
Spring test
Liqubase 4.15 <br>
Hibernate 5.6.11 <br>
h2database 2.14 <br>
Mockito <br>
Lombok 1.18 <br>
Bootstrap 5.3 <br>
Thymeleaf 3.1 <br>

<h3>Требование к окружению</h3>
JDK 17, Maven 3.8, PostgreSQL 14. <br>

<h3>Запуск приложения</h3>

1. Создайте базу данных accidents_db при помощи консоли PostgreSQL или терминала pgAdmin:<br>
   """CREATE DATABASE accidents_db"""
2. Скопировать проект из репозитория по ссылке:
   [Проект автонарушители](https://github.com/dima-abc/jpb4j_accidents.git)<br>
3. Перейдите в корень проекта и при помощи Maven соберите проект командой:<br>
   """mvn install"""
4. После успешной сборки проекта перейдите в каталог собранного проекта <b>target</b> и запустите приложение
   командой:<br>
   """java -jar job4j_accidents-0.0.1-SNAPSHOT.jar"""
5. В браузере перейдите по ссылке http://localhost:8080/index

<h3>Взаимодействие с приложением</h3>

![01_login.jpg](img%2F01_login.jpg) <br>

Рисунок 1. Страница авторизации пользователя <br>

![02_reg.jpg](img%2F02_reg.jpg) <br>

Рисунок 2. Страница регистрации пользователя <br>

![03_index.jpg](img%2F03_index.jpg) <br>

Рисунок 3. Главная страница приложения <br>

![04_all_accidents.jpg](img%2F04_all_accidents.jpg)! <br>

Рисунок 4. Страница отображения всех нарушений <br>

![05_edit_accidents.jpg](img%2F05_edit_accidents.jpg) <br>

Рисунок 5. Страница редактирования нарушения <br>

![06_create_accidents.jpg](img%2F06_create_accidents.jpg) <br>

Рисунок 6. Страница создания нового задания <br>

![07_error_page.jpg](img%2F07_error_page.jpg) <br>

Рисунок 7. Страница ошибки <br>


### Контакты

> email: [haoos@inbox.ru](mailto:haoos@inbox.ru) <br>
> tl: [dima_abc](https://t.me/dima_abc) <br>
> github.com: [dima-abc](https://github.com/dima-abc)
