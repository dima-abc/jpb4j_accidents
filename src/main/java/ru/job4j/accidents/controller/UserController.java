package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.springdata.AuthoritySpringDataRepository;
import ru.job4j.accidents.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.4. Security
 * 0. Spring Security [#6879]
 * UserController обработка видов авторизации
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 19.04.2023
 */
@Controller
@AllArgsConstructor
public class UserController {
    private final PasswordEncoder encoder;
    private final UserService users;
    private final AuthoritySpringDataRepository authority;

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Username or Password is incorrect !!";
        }
        if (logout != null) {
            errorMessage = "You have been successfully logged out !!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "users/login";
    }

    @GetMapping("/reg")
    public String getRegistrationUserPage(@RequestParam(value = "error", required = false) String error,
                                          Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Username is already taken";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "users/registration";
    }

    @PostMapping("/reg")
    public String regSaveUser(@ModelAttribute User user) {
        var authority = this.authority.findByAuthority("ROLE_USER");
        user.setPassword(encoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setAuthority(authority);
        var userSave = this.users.save(user);
        if (userSave.isEmpty() || authority == null) {
            return "redirect:/reg?error=true";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login?logout=true";
    }
}
