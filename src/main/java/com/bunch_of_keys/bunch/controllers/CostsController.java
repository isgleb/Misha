package com.bunch_of_keys.bunch.controllers;

import com.bunch_of_keys.bunch.dto.CostTableDto;
import com.bunch_of_keys.bunch.services.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CostsController {

    @Autowired
    CostService costService;

    @GetMapping("/costs-table")
    public ResponseEntity getCosts() {
        List<CostTableDto> costTableDtos = costService.getCostsForTable();
        return new ResponseEntity(costTableDtos, HttpStatus.OK);
    }
}
