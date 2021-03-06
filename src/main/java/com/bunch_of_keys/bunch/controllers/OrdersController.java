package com.bunch_of_keys.bunch.controllers;

import com.bunch_of_keys.bunch.domain.documents.Address;
import com.bunch_of_keys.bunch.domain.documents.OrderStatus;
import com.bunch_of_keys.bunch.dto.OrderDto;
import com.bunch_of_keys.bunch.dto.TableOrderDto;
import com.bunch_of_keys.bunch.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

// @RestController работает как @Controller но как бы добавляет к каждому методу аннотацию  @ResponceBody
// аннотации @ResponceBody конвертирует java объект в JSON, @RequestBody конвертирует входной JSON в  объект Java

@RestController
public class OrdersController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/orders-table")
    public ResponseEntity getOrders() {
        List<TableOrderDto> tableOrderDtos = orderService.getOrders();
        return new ResponseEntity(tableOrderDtos, HttpStatus.OK);
    }

    @GetMapping("/the-order")
    public ResponseEntity getTheOrder(@RequestParam Long orderId) {
        OrderDto orderDto = orderService.getTheOrder(orderId);
//        System.out.println(orderDto.getDate());
        return new ResponseEntity(orderDto, HttpStatus.OK);
    }

    @DeleteMapping("/orders/request")
    public void deleteOrder(@RequestParam Long id) { // работает с postman
        orderService.deleteOrder(id);
    }


    @PutMapping("/order/customer")
    public void editClientId (@RequestParam Long orderId, Long customerId) {
        orderService.changeCustomer(orderId, customerId);
    }

    @PutMapping("/the-order/update-date")
    public ResponseEntity editDate (@RequestParam Long orderId, @RequestBody Date date) {

        orderService.changeDate(orderId, date);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/the-order/update-status")
    public ResponseEntity editStatus (@RequestParam Long orderId, @RequestBody OrderStatus orderStatus) {

        orderService.changeStatus(orderId, orderStatus);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/the-order/update-address")
    public ResponseEntity editAddress (@RequestParam Long orderId, @RequestBody Address address) {

        orderService.changeAddress(orderId, address);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/the-order/update-meters")
    public ResponseEntity editMeters (@RequestParam Long orderId, @RequestBody Integer meters) {

        orderService.changeMeters(orderId, meters);
        return new ResponseEntity(HttpStatus.OK);
    }


    @PostMapping("/create-new-order")
    public ResponseEntity createOrder(@RequestBody OrderDto orderDto) {
        orderDto = orderService.createNewOrder(orderDto);
        return new ResponseEntity(orderDto, HttpStatus.OK);
    }
}
