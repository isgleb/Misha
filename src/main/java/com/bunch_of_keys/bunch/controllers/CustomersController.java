package com.bunch_of_keys.bunch.controllers;

import com.bunch_of_keys.bunch.domain.CustomerDao;
import com.bunch_of_keys.bunch.domain.OrderDao;
import com.bunch_of_keys.bunch.dto.CustomerDto;
import com.bunch_of_keys.bunch.dto.NewOrderRequest;
import com.bunch_of_keys.bunch.services.CustomerService;
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
public class CustomersController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/customers/request")
    public ResponseEntity getCustomers () {

        Iterable<CustomerDao> customersResp = customerService.getCustomers();

        return new ResponseEntity(customersResp, HttpStatus.OK);
    }

    @GetMapping("/order/customer")
    public CustomerDto getTheCustomer (@RequestParam Long id) {
        Long theCustomerId = orderService.getCustomerId(id);
        CustomerDto customer = customerService.getTheCustomer(theCustomerId);
        return customer;
    }

    @PostMapping("/customers/request")
    public ResponseEntity newOrder (@RequestBody CustomerDto customerDto) {
        customerService.addCustomer(customerDto);
        return new ResponseEntity(customerDto, HttpStatus.OK); // статусы поменять в соответствии с RESTful
    }

    @DeleteMapping("/customers/request")
    public void deleteCustomer (@RequestParam Long id) { // работает с postman
        customerService.deleteCustomer(id);
    }

    @PutMapping("/customers/request")
    public ResponseEntity editOrder (@RequestBody CustomerDto customerDto) {
        return new ResponseEntity(customerService.editCustomer(customerDto), HttpStatus.OK);

    }
}