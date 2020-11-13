package com.bunch_of_keys.bunch.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class Registration {

    @GetMapping("/")
    public String home (Model model) {
        model.addAttribute("title", "главная страничка");
        return "home";
    }

    @GetMapping("/main")
    public String dashboard (Model model) {
        model.addAttribute("title", "главная страничка");
        return "dashboard";
    }

}