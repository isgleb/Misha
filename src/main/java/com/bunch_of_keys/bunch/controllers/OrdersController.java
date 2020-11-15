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
//        orderService.setSomeOrders();

        List<OrderDao> ordersResp = orderService.getOrders();

        return new ResponseEntity(ordersResp, HttpStatus.OK);
    }

    @PostMapping("/orders/request")
    public ResponseEntity deleteOrder (@RequestBody NewOrderRequest newOrderRequest) {
        System.out.println(newOrderRequest.getCustomer());
        orderService.newOrder(newOrderRequest);
        return new ResponseEntity(newOrderRequest, HttpStatus.OK);
    }

//    @DeleteMapping("/orders/request")
//    public ResponseEntity deleteOrder (@RequestParam String id) {
//        System.out.println(id);
//        orderService.newOrder(newOrderRequest);
//        return new ResponseEntity(newOrderRequest, HttpStatus.OK);
//    }

}