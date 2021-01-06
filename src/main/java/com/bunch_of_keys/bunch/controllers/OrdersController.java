package com.bunch_of_keys.bunch.controllers;

import com.bunch_of_keys.bunch.dto.CustomerDto;
import com.bunch_of_keys.bunch.dto.OrderDto;
import com.bunch_of_keys.bunch.dto.TableOrderDto;
import com.bunch_of_keys.bunch.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

// @RestController работает как @Controller но как бы добавляет к каждому методу аннотацию  @ResponceBody
// аннотации @ResponceBody конвертирует java объект в JSON, @RequestBody конвертирует входной JSON в  объект Java

@RestController
public class OrdersController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/orders-table")
    public ResponseEntity getOrders() {
        List<TableOrderDto> tableOrderDto = orderService.getOrders();
        return new ResponseEntity(tableOrderDto, HttpStatus.OK);
    }

    @DeleteMapping("/orders/request")
    public void deleteOrder(@RequestParam Long id) { // работает с postman
        orderService.deleteOrder(id);
    }


    @PutMapping("/order/customer")
    public void editClientId (@RequestParam Long orderId, Long customerId) {
        orderService.changeCustomer(orderId, customerId);
    }

    @PostMapping("/create-new-order")
    public ResponseEntity createOrder(@RequestBody OrderDto orderDto) {
        System.out.println(orderDto);
//        orderDto = orderService.createNewOrder(orderDto);
        return new ResponseEntity(orderDto, HttpStatus.OK);
    }
}
