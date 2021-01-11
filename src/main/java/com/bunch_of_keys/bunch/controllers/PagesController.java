package com.bunch_of_keys.bunch.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class PagesController {


    @GetMapping("/")
    public String main (Model model) {
        model.addAttribute("title", "главная страничка");
        return "table-orders";
    }

    @GetMapping("/orders")
    public String tabelOrders (Model model) {
        model.addAttribute("title", "главная страничка");
        return "table-orders";
    }

    @GetMapping("/customers")
    public String customers (Model model) {
        model.addAttribute("title", "главная страничка");
        return "customers";
    }

    @GetMapping("/order/{orderId}")
    public String orderpage () {
        return "order-page";
    }


    @GetMapping("/template")
    public String template () {
        return "template";
    }

    @GetMapping("/services")
    public String services () {
        return "services";
    }

    @GetMapping("/cost/types")
    public String invoicePositions () {
        return "cost-types";
    }

    @GetMapping("/stuff")
    public String stuff () {
        return "stuff";
    }

    @GetMapping("/cost")
    public String costs () {
        return "costs";
    }

    @GetMapping("/new-order")
    public String newOrder () {
        return "new-order";
    }

    @GetMapping("/grid")
    public String grid () {
        return "grid";
    }

    @GetMapping("/test")
    public String test () {
        return "test";
    }

}