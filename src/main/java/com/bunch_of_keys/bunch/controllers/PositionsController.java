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
    public ResponseEntity getServices () {

        List<PositionDto> positionDtos = positionService.getPositions();

        return new ResponseEntity(positionDtos, HttpStatus.OK);
    }

    @PostMapping("/positions/request")
    public ResponseEntity newService (@RequestBody PositionDto positionDto) {
        positionService.addPosition(positionDto);
        return new ResponseEntity(positionDto, HttpStatus.OK);
    }
//
//    @DeleteMapping("/positions/request")
//    public void deleteService (@RequestParam Long id) { // работает с postman
//        cleaningServService.deleteService(id);
//    }
//
//    @PutMapping("/positions/request")
//    public ResponseEntity editService (@RequestBody ServiceDto serviceDto) {
//        return new ResponseEntity(cleaningServService.editService(serviceDto), HttpStatus.OK);
//    }

}
