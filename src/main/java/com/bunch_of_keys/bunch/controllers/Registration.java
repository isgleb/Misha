package com.bunch_of_keys.bunch.controllers;

import com.bunch_of_keys.bunch.domain.OrderDao;
import com.bunch_of_keys.bunch.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


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

    @GetMapping("/orders")
    public String orders (Model model) {
        model.addAttribute("title", "главная страничка");
        return "tables";
    }

    @GetMapping("/orders/request")
    public ResponseEntity getOrders (Model model) {
        OrderService os = new OrderService();
        os.setOrders();

        List<OrderDao> ordersResp = os.getOrders();

        return new ResponseEntity(ordersResp, HttpStatus.OK);
    }

    @GetMapping("/alter")
    public String alter (Model model) {
        model.addAttribute("title", "главная страничка");
        return "example2";
    }



}