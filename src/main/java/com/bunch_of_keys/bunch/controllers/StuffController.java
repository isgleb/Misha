package com.bunch_of_keys.bunch.controllers;

import com.bunch_of_keys.bunch.dto.StuffDto;
import com.bunch_of_keys.bunch.services.StuffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class StuffController {

    @Autowired
    private StuffService stuffService;

    @GetMapping("/stuff/request")
    public ResponseEntity getStuff () {

        Iterable<StuffDto> stuffResp = stuffService.getStuff();

        return new ResponseEntity(stuffResp, HttpStatus.OK);
    }

    @PostMapping("/stuff/request")
    public ResponseEntity newStuff (@RequestBody StuffDto stuffDto) {
        stuffService.addStuff(stuffDto);
        return new ResponseEntity(stuffDto, HttpStatus.OK); // статусы поменять в соответствии с RESTful
    }

    @DeleteMapping("/stuff/request")
    public void deleteStuff (@RequestParam Long id) {
        stuffService.deleteStuff(id);
    }

    @PutMapping("/stuff/request")
    public ResponseEntity editStuff (@RequestBody StuffDto customerDto) {
        return new ResponseEntity(stuffService.editStuff(customerDto), HttpStatus.OK);

    }

//    @GetMapping("/order/customer")
//    public ResponseEntity getCustomerByorder (@RequestParam Long orderId) {
//        StuffDto customersResp = stuffService.getTheCustomer(orderId);
//        return new ResponseEntity(customersResp, HttpStatus.OK);
//    }
}
