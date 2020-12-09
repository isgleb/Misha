package com.bunch_of_keys.bunch.controllers;

import com.bunch_of_keys.bunch.dto.CustomerDto;
import com.bunch_of_keys.bunch.dto.InvoiceDto;
import com.bunch_of_keys.bunch.dto.ServiceDto;
import com.bunch_of_keys.bunch.dto.TableOrderDto;
import com.bunch_of_keys.bunch.services.InvoiceService;
import com.bunch_of_keys.bunch.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;


    @GetMapping("/invoice/request")
    public ResponseEntity getTheInvoice(@RequestParam Long orderId) {
        List<InvoiceDto> invoiceDtos = invoiceService.getInvoicesByOrder(orderId);
        return new ResponseEntity(invoiceDtos, HttpStatus.OK);
    }

    @PostMapping("/invoice/request")
    public ResponseEntity newInvoice (@RequestBody InvoiceDto invoiceDto) {
        invoiceService.newOrderInvoice(invoiceDto);
        return new ResponseEntity(invoiceDto, HttpStatus.OK);
    }







    @DeleteMapping("/invoice/request")
    public void deleteCustomer (@RequestParam Long id) { // работает с postman
        invoiceService.deleteInvoice(id);
    }

//    @PutMapping("/customers/request")
//    public ResponseEntity editCustomer (@RequestBody CustomerDto customerDto) {
//        return new ResponseEntity(customerService.editCustomer(customerDto), HttpStatus.OK);
//
//    }




}
