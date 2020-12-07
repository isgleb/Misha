package com.bunch_of_keys.bunch.controllers;

import com.bunch_of_keys.bunch.domain.Position;
import com.bunch_of_keys.bunch.dto.PositionDto;
import com.bunch_of_keys.bunch.dto.ServiceDto;
import com.bunch_of_keys.bunch.services.CleaningServService;
import com.bunch_of_keys.bunch.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PositionsController {

    @Autowired
    private PositionService positionService;

    @GetMapping("/positions/request")
    public ResponseEntity getPositios(@RequestParam Long orderId) {

        List<PositionDto> positionDtos = positionService.getPositions(orderId);

        return new ResponseEntity(positionDtos, HttpStatus.OK);
    }

    @PostMapping("/positions/request")
    public ResponseEntity newPosition(@RequestBody PositionDto positionDto, @RequestParam Long orderId) {
        positionService.addPosition(positionDto, orderId);
        return new ResponseEntity(positionDto, HttpStatus.OK);
    }

    @DeleteMapping("/positions/request")
    public void deletePosition(@RequestParam Long id) { // работает с postman
        positionService.deleteService(id);
    }

    @PutMapping("/positions/request")
    public ResponseEntity editService(@RequestBody PositionDto positionDto, @RequestParam Long orderId) {
        return new ResponseEntity(positionService.editService(positionDto, orderId), HttpStatus.OK);
    }




//    @PutMapping("/positions/request")
//    public void editService (HttpEntity<String> httpEntity) {
//        String json = httpEntity.getBody();
//        System.out.println(json);
//        return new ResponseEntity(positionService.editService(positionDto), HttpStatus.OK);
//    }




}
