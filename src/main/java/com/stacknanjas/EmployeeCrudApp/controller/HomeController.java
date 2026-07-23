package com.stacknanjas.EmployeeCrudApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String redirectToEmployees() {
        return "redirect:/employees";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
