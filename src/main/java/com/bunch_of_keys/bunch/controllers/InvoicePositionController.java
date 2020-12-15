package com.bunch_of_keys.bunch.controllers;

import com.bunch_of_keys.bunch.dto.InvoicePositionDto;
import com.bunch_of_keys.bunch.dto.CostTypeDto;
import com.bunch_of_keys.bunch.services.InvoicePositionService;
import com.bunch_of_keys.bunch.services.CostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoicePositionController {

    @Autowired
    private InvoicePositionService invoicePositionService;

    @GetMapping("/invoicePositions/request")
    public ResponseEntity getCosts () {

        List<InvoicePositionDto> invoicePositionDtos = invoicePositionService.getCosts();

        return new ResponseEntity(invoicePositionDtos, HttpStatus.OK);
    }

    @PostMapping("/invoicePositions/request")
    public ResponseEntity newCost (@RequestBody InvoicePositionDto invoicePositionDto) {
        invoicePositionService.addInvoicePosition(invoicePositionDto);
        return new ResponseEntity(invoicePositionDto, HttpStatus.OK);
    }

    @DeleteMapping("/invoicePositions/request")
    public void deleteCost (@RequestParam Long id) { // работает с postman
        invoicePositionService.deleteInvoicePosition(id);
    }

    @PutMapping("/invoicePositions/request")
    public ResponseEntity editCost (@RequestBody InvoicePositionDto invoicePositionDto) {
        return new ResponseEntity(invoicePositionService.editInvoicePosition(invoicePositionDto), HttpStatus.OK);
    }


}
