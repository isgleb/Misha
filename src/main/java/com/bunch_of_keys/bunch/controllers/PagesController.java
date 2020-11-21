package com.bunch_of_keys.bunch.controllers;

import com.bunch_of_keys.bunch.domain.OrderDao;
import com.bunch_of_keys.bunch.dto.NewOrderRequest;
import com.bunch_of_keys.bunch.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class PagesController {

    @GetMapping("/")
    public String home (Model model) {
        model.addAttribute("title", "главная страничка");
        return "index2";
    }

    @GetMapping("/main")
    public String dashboard (Model model) {
        model.addAttribute("title", "главная страничка");
        return "dashboard";
    }

    @GetMapping("/d.html")
    public String d (Model model) {
        model.addAttribute("title", "главная страничка");
        return "d";
    }

    @GetMapping("/c.html")
    public String c (Model model) {
        model.addAttribute("title", "главная страничка");
        return "c";
    }

    @GetMapping("/index")
    public String index (Model model) {
        model.addAttribute("title", "главная страничка");
        return "index";
    }

    @GetMapping("/page1.html")
    public String pagefirst () {
        return "page1";
    }

    @GetMapping("/page2.html")
    public String pagesecond () {
        return "page2";
    }


    @GetMapping("/alter")
    public String alter (Model model) {
        model.addAttribute("title", "главная страничка");
        return "example2";
    }


    @GetMapping("/alteranother")
    public String alterAnother (Model model) {
        model.addAttribute("title", "главная страничка");
        return "example3";
    }
    @GetMapping("/alterf")
    public String alterF (Model model) {
        model.addAttribute("title", "главная страничка");
        return "example4";
    }
}