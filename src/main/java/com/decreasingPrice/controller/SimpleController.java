package com.decreasingPrice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SimpleController {

    @GetMapping({"/", "welcome**"})
    public String homePage(Model model) {
        model.addAttribute("title", "Spring Security Hello World");
        model.addAttribute("message", "This is welcome page!");
        return "hello";
    }

    @GetMapping("/admin**")
    public String adminPage(Model model) {
        model.addAttribute("title", "Spring Security Hello World");
        model.addAttribute("message", "This is protected page - Admin Page!");
        return "admin";
    }

    @GetMapping("/dba**")
    public String dataBasePage(Model model) {
        model.addAttribute("title", "Spring Security Hello World");
        model.addAttribute("message", "This is protected page - Database Page!");
        return "admin";
    }
}
