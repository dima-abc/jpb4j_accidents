package ru.job4j.accidents.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 0. Spring MVC [#6877]
 * IndexController Контроллер стартовой страницы
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 16.04.2023
 */
@Controller
public class IndexController {
    @GetMapping({"/", "index"})
    public String getIndexPage() {
        return "index";
    }
}
