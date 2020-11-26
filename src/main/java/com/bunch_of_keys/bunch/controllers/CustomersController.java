package com.bunch_of_keys.bunch.controllers;

import com.bunch_of_keys.bunch.domain.Customer;
import com.bunch_of_keys.bunch.domain.Order;
import com.bunch_of_keys.bunch.dto.CustomerDto;
import com.bunch_of_keys.bunch.services.CustomerService;
import com.bunch_of_keys.bunch.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// @RestController работает как @Controller но как бы добавляет к каждому методу аннотацию  @ResponceBody
// аннотации @ResponceBody конвертирует java объект в JSON, @RequestBody конвертирует входной JSON в  объект Java

@RestController
public class CustomersController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers/request")
    public ResponseEntity getCustomers () {

        Iterable<CustomerDto> customersResp = customerService.getCustomers();

        return new ResponseEntity(customersResp, HttpStatus.OK);
    }

    @PostMapping("/customers/request")
    public ResponseEntity newCustomer (@RequestBody CustomerDto customerDto) {
        customerService.addCustomer(customerDto);
        return new ResponseEntity(customerDto, HttpStatus.OK); // статусы поменять в соответствии с RESTful
    }

    @DeleteMapping("/customers/request")
    public void deleteCustomer (@RequestParam Long id) { // работает с postman
        customerService.deleteCustomer(id);
    }

    @PutMapping("/customers/request")
    public ResponseEntity editCustomer (@RequestBody CustomerDto customerDto) {
        return new ResponseEntity(customerService.editCustomer(customerDto), HttpStatus.OK);

    }

    @GetMapping("/order/customer")
    public ResponseEntity getCustomers (@RequestParam Long orderId) {

        CustomerDto customersResp = customerService.getTheCustomer(orderId);

        return new ResponseEntity(customersResp, HttpStatus.OK);
    }



}