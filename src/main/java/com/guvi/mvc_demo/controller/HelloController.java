package com.guvi.mvc_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/welcome")
    public String greeting(Model model) {
        model.addAttribute("message", "Welcome to your first Spring MVC Demo!");
        return "welcome"; // This points to welcome.html
    }
}
