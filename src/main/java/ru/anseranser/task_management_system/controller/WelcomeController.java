package ru.anseranser.task_management_system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/")
public class WelcomeController {
    @GetMapping
    public String welcome() {
        return "Welcome to Spring";
    }
}

