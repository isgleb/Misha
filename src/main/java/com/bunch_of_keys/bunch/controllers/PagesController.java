package com.bunch_of_keys.bunch.controllers;

import com.bunch_of_keys.bunch.domain.Order;
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

}