package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "user-list";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add")
    public String showAddForm() {
        return "user-add";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String addUser(@RequestParam String name,
                          @RequestParam String surname,
                          @RequestParam Integer age,
                          @RequestParam String username,
                          @RequestParam String password) {
        userService.createUser(name, surname, age, username, password);
        return "redirect:/users";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit")
    public String showEditForm(@RequestParam Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-edit";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/edit")
    public String updateUser(@RequestParam Long id,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam Integer age) {
        userService.updateUserFields(id, name, surname, age);
        return "redirect:/users";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.removeUser(id);
        return "redirect:/users";
    }
}
