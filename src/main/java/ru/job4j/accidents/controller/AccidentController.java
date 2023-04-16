package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.accidents.service.AccidentService;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 16.04.2023
 */
@Controller
@AllArgsConstructor
@RequestMapping("/accidents")
public class AccidentController {
    private final AccidentService accidentService;

    @GetMapping("/list")
    public String getAllAccidents(Model model) {
        model.addAttribute("user", "Dmitry");
        model.addAttribute("accidents", accidentService.findAll());
        return "accidents/list";
    }
}
