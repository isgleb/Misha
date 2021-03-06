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
    public ResponseEntity getInvoicesByOrder(@RequestParam Long orderId) {
        List<InvoiceDto> invoiceDtos = invoiceService.getInvoicesByIRD(orderId);
        return new ResponseEntity(invoiceDtos, HttpStatus.OK);
    }

    @GetMapping("/the-invoice")
    public ResponseEntity getTheInvoiceByCost(@RequestParam Long costId) {
        InvoiceDto invoiceDto = invoiceService.getInvoicesByIRD(costId).get(0);;
        return new ResponseEntity(invoiceDto, HttpStatus.OK);
    }

    @PostMapping("/invoice/request")
    public ResponseEntity newOrderInvoice (@RequestBody InvoiceDto invoiceDto) {
        invoiceService.newOrderInvoice(invoiceDto);
        return new ResponseEntity(invoiceDto, HttpStatus.OK);
    }

    @PostMapping("/new-invoices/array")
    public ResponseEntity newOrderInvoices (@RequestBody List<InvoiceDto> invoiceDtos) {

        for (InvoiceDto invoiceDto : invoiceDtos) {
            invoiceService.newOrderInvoice(invoiceDto);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/new-invoice")
    public ResponseEntity newCostInvoices (@RequestBody InvoiceDto invoiceDto) {
        invoiceDto = invoiceService.newInvoice(invoiceDto);
        return new ResponseEntity(invoiceDto, HttpStatus.OK);
    }


    @DeleteMapping("/invoice/request")
    public void deleteCustomer (@RequestParam Long id) { invoiceService.deleteInvoice(id);}

    @PutMapping("/invoice/request")
    public ResponseEntity editInvoice (@RequestBody InvoiceDto invoiceDto) {
        return new ResponseEntity(invoiceService.editInvoice(invoiceDto), HttpStatus.OK);
    }

}
