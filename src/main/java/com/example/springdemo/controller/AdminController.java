package com.example.springdemo.controller;

import com.example.springdemo.model.Role;
import com.example.springdemo.model.User;
import com.example.springdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/userlist")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "/admin/userlist";
    }

    @GetMapping("/new-user")
    public String createUserForm(@ModelAttribute("user")User user, Model model) {
        List<Role> listRoles = userService.listRoles();
        model.addAttribute("listRoles", listRoles);
        return "/admin/newuser";
    }

    @PostMapping("/new-user")
    public String createUser(@ModelAttribute("user")User user) {
        userService.createUpdateUser(user);
        return "redirect:/admin/userlist";
    }

    @GetMapping(value = "/update-user/{id}")
    public String formUpdateUser(@PathVariable Long id, Model model) {
        List<Role> listRoles = userService.listRoles();
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("listRoles", listRoles);
        return "/admin/edituser";
    }

    @PostMapping(value = "/update-user/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.createUpdateUser(user);
        return "redirect:/admin/userlist";
    }

    @GetMapping(value = "/user-delete/{id}")
    public String removeUser(@PathVariable Long id) {
        userService.deleteById(userService.getUserById(id));
        return "redirect:/admin/userlist";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user/user";
    }
}
