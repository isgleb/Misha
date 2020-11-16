package com.bunch_of_keys.bunch.controllers;

import com.bunch_of_keys.bunch.domain.OrderDao;
import com.bunch_of_keys.bunch.dto.NewOrderRequest;
import com.bunch_of_keys.bunch.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders/request")
    public ResponseEntity getOrders () {
        orderService.setSomeOrders();

        List<OrderDao> ordersResp = orderService.getOrders();

        return new ResponseEntity(ordersResp, HttpStatus.OK);
    }

    @PostMapping("/orders/request")
    public ResponseEntity newOrder (@RequestBody NewOrderRequest newOrderRequest) {
        orderService.newOrder(newOrderRequest);
        return new ResponseEntity(newOrderRequest, HttpStatus.OK); // статусы поменять в соответствии с RESTful
    }

    @DeleteMapping("/orders/request")
    public void deleteOrder (@RequestParam Integer id) { // работает с postman
        System.out.println(id);
    }

    @PutMapping("/orders/request")
    public void editOrder (@RequestParam Integer id) { // работает с postman
        System.out.println(id);
//        orderService.newOrder(newOrderRequest);
//        return new ResponseEntity(newOrderRequest, HttpStatus.OK);
    }
}