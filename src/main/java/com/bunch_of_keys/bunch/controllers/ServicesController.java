package com.bunch_of_keys.bunch.controllers;

import com.bunch_of_keys.bunch.dto.ServiceDto;
import com.bunch_of_keys.bunch.services.CleaningServService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController работает как @Controller но как бы добавляет к каждому методу аннотацию  @ResponceBody
// аннотации @ResponceBody конвертирует java объект в JSON, @RequestBody конвертирует входной JSON в  объект Java

@RestController
public class ServicesController {

    @Autowired
    private CleaningServService cleaningServService;

    @GetMapping("/services/request")
    public ResponseEntity getServices () {

        List<ServiceDto> serviceDtos = cleaningServService.getServices();

        return new ResponseEntity(serviceDtos, HttpStatus.OK);
    }

    @PostMapping("/services/request")
    public ResponseEntity newService (@RequestBody ServiceDto serviceDto) {
        cleaningServService.addService(serviceDto);
        return new ResponseEntity(serviceDto, HttpStatus.OK);
    }

    @DeleteMapping("/services/request")
    public void deleteService (@RequestParam Long id) { // работает с postman
        cleaningServService.deleteService(id);
    }

    @PutMapping("/services/request")
    public ResponseEntity editService (@RequestBody ServiceDto serviceDto) {
        return new ResponseEntity(cleaningServService.editService(serviceDto), HttpStatus.OK);
    }
}