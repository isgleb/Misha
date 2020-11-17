package com.bunch_of_keys.bunch.controllers;

import com.bunch_of_keys.bunch.domain.OrderDao;
import com.bunch_of_keys.bunch.dto.NewOrderRequest;
import com.bunch_of_keys.bunch.services.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// @RestController работает как @Controller но как бы добавляет к каждому методу аннотацию  @ResponceBody
// аннотации @ResponceBody конвертирует java объект в JSON, @RequestBody конвертирует входной JSON в  объект Java

@RestController
public class OrdersController {

    @Autowired
    private OrderService orderService;
    //    service работает с DTO. он будет принимать id и возвращать объект DTO либо List<DTO>
//    service реализует всю бизнеслогику

    @GetMapping("/orders/request")
    public ResponseEntity getOrders () {
//        orderService.setSomeOrders();

        List<NewOrderRequest> ordersResp = orderService.getOrders();

        return new ResponseEntity(ordersResp, HttpStatus.OK);
    }

    @PostMapping("/orders/request")
    public ResponseEntity newOrder (@RequestBody NewOrderRequest newOrderRequest) {
        orderService.newOrder(newOrderRequest);
        return new ResponseEntity(newOrderRequest, HttpStatus.OK); // статусы поменять в соответствии с RESTful
    }

    @DeleteMapping("/orders/request")
    public void deleteOrder (@RequestParam Long id) { // работает с postman
        orderService.deleteOrder(id);
    }

    @PutMapping("/orders/request")
    public ResponseEntity editOrder (@RequestBody NewOrderRequest newOrderRequest) { // работает с postman
        return new ResponseEntity(orderService.editOrder(newOrderRequest), HttpStatus.OK);

//        orderService.newOrder(newOrderRequest);
//        return new ResponseEntity(newOrderRequest, HttpStatus.OK);
    }
}