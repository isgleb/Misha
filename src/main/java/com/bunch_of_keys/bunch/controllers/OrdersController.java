package com.bunch_of_keys.bunch.controllers;

import com.bunch_of_keys.bunch.domain.Order;
import com.bunch_of_keys.bunch.dto.OrderDto;
import com.bunch_of_keys.bunch.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController работает как @Controller но как бы добавляет к каждому методу аннотацию  @ResponceBody
// аннотации @ResponceBody конвертирует java объект в JSON, @RequestBody конвертирует входной JSON в  объект Java

@RestController
public class OrdersController {

    @Autowired
    private OrderService orderService;
    //    service работает с DTO. он будет принимать id и возвращать объект DTO либо List<DTO>
//    service реализует всю бизнеслогику

    @GetMapping("/orders/request")
    public ResponseEntity getOrders() {
//        orderService.setSomeOrders();

        List<OrderDto> ordersResp = orderService.getOrders();

        return new ResponseEntity(ordersResp, HttpStatus.OK);
    }

    @PostMapping("/orders/request")
    public ResponseEntity newOrder(@RequestBody OrderDto orderDto) {
        orderService.newOrder(orderDto);
        return new ResponseEntity(orderDto, HttpStatus.OK); // статусы поменять в соответствии с RESTful
    }

    @DeleteMapping("/orders/request")
    public void deleteOrder(@RequestParam Long id) { // работает с postman
        orderService.deleteOrder(id);
    }

    @PutMapping("/orders/request")
    public ResponseEntity editOrder(@RequestBody OrderDto orderDto) { // работает с postman
        return new ResponseEntity(orderService.editOrder(orderDto), HttpStatus.OK);
    }


    @PutMapping("/order/customer")
    public void editClientId (@RequestParam Long orderId, Long customerId) {
        System.out.println(orderId);
        System.out.println(customerId);
//        orderService.changeCustomer

    }
}
