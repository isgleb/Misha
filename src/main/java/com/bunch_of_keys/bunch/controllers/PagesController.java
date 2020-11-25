package com.bunch_of_keys.bunch.controllers;

import com.bunch_of_keys.bunch.domain.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class PagesController {

    @GetMapping("/no")
    public String home (Model model) {
        model.addAttribute("title", "главная страничка");
        return "index2";
    }

    @GetMapping("/")
    public String main (Model model) {
        model.addAttribute("title", "главная страничка");
        return "main";
    }

    @GetMapping("/orders")
    public String tabelorders (Model model) {
        model.addAttribute("title", "главная страничка");
        return "tableorders";
    }

    @GetMapping("/order")
    public String orderpage () {
        return "order-page";
    }
























    @GetMapping("/index")
    public String index (Model model) {
        model.addAttribute("title", "главная страничка");
        return "index";
    }

    @GetMapping("/orders/table.html")
    public String pagefirst () {
        return "ordertable";
    }

    @GetMapping("/bread.html")
    public String bread () {
        return "bread";
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